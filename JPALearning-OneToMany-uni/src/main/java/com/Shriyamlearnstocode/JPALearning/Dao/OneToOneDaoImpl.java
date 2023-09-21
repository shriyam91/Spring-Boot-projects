package com.Shriyamlearnstocode.JPALearning.Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Shriyamlearnstocode.JPALearning.Entity.Course;
import com.Shriyamlearnstocode.JPALearning.Entity.InstructorDetail;
import com.Shriyamlearnstocode.JPALearning.Entity.InstructorMan;

import jakarta.persistence.EntityManager;
@Repository
public class OneToOneDaoImpl implements OneToOneDao{
	EntityManager entityManager;
	
	@Autowired
	public OneToOneDaoImpl(EntityManager theEntity) {
		this.entityManager= theEntity;
	}
	@Transactional
	@Override
	public InstructorMan save(InstructorMan theInstructor) {
		//Session session= entityManager.unwrap(Session.class);
		entityManager.persist(theInstructor);
		//session.persist(theInstructor);
			//InstructorMan savedOne= session.persist(theInstructor)	;
		
		return theInstructor;//getSingleresult method returns the saved entity
		
		// TODO Auto-generated method st
		
	}
	
	@Override
	public InstructorMan findInstructorById(int theId) {
		Session session= entityManager.unwrap(Session.class);
	//	InstructorMan foundInst =session.get(InstructorMan.class,theId );
		//Query<InstructorMan> ql= session.createQuery("From InstructorMan  Where id= :theid");
		Query<InstructorMan> ql= session.createQuery("From InstructorMan  Where id= :theid",InstructorMan.class);
//note that when writting a jpql query for finding an entry from the table we can write query in 
// both of the above ways ie with class name in parameter column and without it also
//but when writting jqpl query for deleting and updating then query will only contain only
//the query and not the class name and if written then it will give "org.hibernate.query.IllegalQueryOperationException:
		//Result type given for a non-SELECT Query " this kind of error
	return 	ql.setParameter("theid", theId).getSingleResult();
	//note that this will also return instructordetail with it also
	//	return foundInst;
	}
	@Transactional
	@Override
	public void deleteInstructorById(int theId) {
		//Session session= entityManager.unwrap(Session.class);
		InstructorMan im= entityManager.find(InstructorMan.class, theId);
		//note: since instructor is having the onetomany relation with the course and instructor is the child
		//table and the course is the parent table(since fk is in the course table) so we cannot delete the instructor either without:
			//without deleting the course(deleting the course will also delete the referenced instructor and if we only
			//want to delete the instructor without deleting the courses then we just need to break the fk reference b/w
			// the parent and the child table: we will do it in the dao implementation class
		List<Course> cs= im.getCourses();
		for(Course cc: cs) {
			cc.setInstructor(null);
		}
		 entityManager.remove(im);
		
	//	Query<InstructorMan> ql=session.createQuery("Delete From InstructorMan Where id = :theid");
//Note:when writting jqpl query for deleting and updating then query will only contain query and not the class as a parameter
		//the query and not the class name 
		

	//	ql.setParameter("theid", theId).executeUpdate();
		//entityManager.createQuery("Delete From InstructorMan Where id= theid")
		//.setParameter("theid", theId)
			//		.executeUpdate();
		//3rd method to write query using native methods
				//InstructorMan tobeRemoved= entityManager.find(InstructorMan.class, theId);
				//entityManager.remove(tobeRemoved);//this query method is deleting both instructordetail and the associated instructor
//			entityManager.createQuery("Delete From InstructorMan Where instructorDetail.id= :theid").
//			setParameter("theid", theId).
//			executeUpdate();
	//note: we cannot delete both the parent entity and child entity with custom jpql queries, for this we have to use
	//conventional/native queries.
	
	}
	@Override
	public InstructorDetail findInstructorDetailById(int theId) {
		Session session= entityManager.unwrap(Session.class);
		//	InstructorMan foundInst =session.get(InstructorMan.class,theId );
			//Query<InstructorMan> ql= session.createQuery("From InstructorMan  Where id= :theid");
			Query<InstructorDetail> ql= session.createQuery("From InstructorDetail  Where id= :theid",InstructorDetail.class);
	//note that when writting a jpql query for finding an entry from the table we can write query in 
	// both of the above ways ie with class name in parameter column and without it also
	//but when writting jqpl query for deleting and updating then query will only contain only
	//the query and not the class name and if written then it will give "org.hibernate.query.IllegalQueryOperationException:
			//Result type given for a non-SELECT Query " this kind of error
		return 	ql.setParameter("theid", theId).getSingleResult();
		
	}
	@Transactional
	@Override
	public void deleteInstructorDetailById(int theId) {
		//Session session= entityManager.unwrap(Session.class);
	//1st method to write query:	using session
	//Query<InstructorDetail> ql=session.createQuery("Delete From InstructorDetail Where id = :theid");
//		ql.setParameter("theid", theId)
//		.executeUpdate();
	//2nd method to write query using directly entitymanager object
		entityManager.createQuery("Delete From InstructorDetail Where id = :theid")
	.setParameter("theid", theId)
				.executeUpdate();
	//3rd method to write query using native methods
		//InstructorDetail tobeRemoved= entityManager.find(InstructorDetail.class, theId);
	//	entityManager.remove(tobeRemoved);//this query method is deleting both instructordetail and the associated instructor
		//Note:when writting jqpl query for deleting and updating then query will only contain only
		//the query and not the class name 
		
		
	//	return ql.executeUpdate();
	//note that this will also return instructordetail with it also
	
	}
	@Override
	public List<Object[]> findInstructorByHobby(String hobby) {
		
		List<Object[]> lit= entityManager.createQuery("Select firstName, lastName From InstructorMan Where instructorDetail.hobby= :data")
		.setParameter("data", hobby).getResultList();
	//Note that when whenever we are selection only few specific entries from one table or from 2 tables then
//we always have to assign them to a list of array of objects bcz each line of row(here each row will consists of first
//name and last name) will be saved as a array of object,and for ex the above ex will select chad-darby as one object array
//and abinav bengali as 2nd array object
		
		return lit;
		
	}
	@Override
	public List<Object[]> selectingFromBothTableUsingJoin() {
		//List<Object[]> litt= entityManager.createQuery("Select firstName, lastName, instructorDetail.hobby From InstructorMan  Inner Join instructorDetail ")
	//	.getResultList();//note this query will not work bcz in mysql while performing joins writing alias is optional but
	//in jpql while writing joins alias is must bcz here in jpql we explicitly do not specify the FK relation b/w parent
//and child table as we do in mysql like in mysql: ....from InstructorMan Inner Join InstructorDetail on InstructorMan.instructor_detail_id= InstructorDetail.id
		List<Object[]> litt= entityManager.createQuery("Select I.firstName, I.lastName, Idt.hobby From InstructorMan I Inner Join I.instructorDetail Idt")
		.getResultList();
	//Note that when whenever we are selection only few specific entries from one table or from 2 tables then
//we always have to assign them to a list of array of objects bcz each line of row(here each row will consists of first
//name and last name) will be saved as a array of object,and for ex the above ex will select chad-darby as one object array
//and abinav bengali as 2nd array object
		
		return litt;
		
	}
	@Transactional
	@Override
	public void updateInstructorDetailByHobby(String firstName, String lastName) {
		entityManager.createQuery("Update InstructorDetail set hobby= 'Direction' Where instructorMan.firstName=:fname And instructorMan.lastName= :lname")
		.setParameter("fname", firstName).setParameter("lname", lastName)
		.executeUpdate();
	}
	@Transactional
	@Override
	public void updateInstructorManByFirstNameAndLastName(String firstName, String lastName) {
		entityManager.createQuery("Update InstructorMan set email= 'AbhinavMahaajan@gmail.com' Where firstName=:fname And lastName= :lname")
		.setParameter("fname", firstName).setParameter("lname", lastName)
		.executeUpdate();
		
	}
	@Transactional
	@Override
	public void updateInstructorManByHobby(String theHobby) {
//		entityManager.createQuery("Update InstructorMan set email= 'AbhinavMahaajan@gmail.com' Where firstName=:fname And lastName= :lname")
//		.setParameter("fname", firstName).setParameter("lname", lastName)
//		.executeUpdate();
		entityManager.createQuery("Update InstructorMan I Inner Join I.instructorDetail Idt set I.email= 'AbhinavMahaajanHealthAndFitness@gmail.com' Where Idt.hobby= :thehobby" )
		.setParameter("thehobby", theHobby)
		.executeUpdate();
	}
	@Override
	public List<Course> findCoursesByInstructorId(int theId) {
		// TODO Auto-generated method stub
	List<Course > cc=	entityManager.createQuery("From Course Where instructorMan.id= :data",Course.class).setParameter("data", theId).getResultList();
		return cc;
	}
	@Override
	public InstructorMan findInstructorByIdJoinFetch(int theId) {
		InstructorMan im= entityManager.createQuery("From instructorMan I Join Fetch I.courses Where id= data", InstructorMan.class).setParameter("data", theId).getSingleResult();
		return im;//join fetch will is used to fetch the courses even when fetchtype in onetomany relationship
	//is set to lazy 
	}
	@Transactional
	@Override
	public void deleteCourseById(int theId) {
		Course cc= entityManager.find(Course.class, theId);
		entityManager.remove(cc);
		
	}
	@Transactional
	@Override
	public void saveCourse(Course course) {
		entityManager.persist(course);
		
	}
	@Override
	public Course findCourseById(int theId) {
		return  entityManager.find(Course.class, theId);
		
	}

}
