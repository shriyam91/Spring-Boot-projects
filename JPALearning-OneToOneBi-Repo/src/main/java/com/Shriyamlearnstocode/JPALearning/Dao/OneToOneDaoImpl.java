package com.Shriyamlearnstocode.JPALearning.Dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
		Session session= entityManager.unwrap(Session.class);
		session.persist(theInstructor);
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
		Session session= entityManager.unwrap(Session.class);
		//Query<InstructorMan> ql= session.createQuery("Delete From InstructorMan  Where id= :theid",InstructorMan.class);
	//	Query<InstructorMan> ql=session.createQuery("Delete From InstructorMan Where id = :theid");
//Note:when writting jqpl query for deleting and updating then query will only contain only
		//the query and not the class name 
		

	//	ql.setParameter("theid", theId).executeUpdate();
		//entityManager.createQuery("Delete From InstructorMan Where id= theid")
		//.setParameter("theid", theId)
			//		.executeUpdate();
		//3rd method to write query using native methods
				//InstructorMan tobeRemoved= entityManager.find(InstructorMan.class, theId);
				//entityManager.remove(tobeRemoved);//this query method is deleting both instructordetail and the associated instructor
			entityManager.createQuery("Delete From InstructorMan Where id= :theid").
			setParameter("theid", theId).
			executeUpdate();
	//note that this will also return instructordetail with it also
	
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


}
