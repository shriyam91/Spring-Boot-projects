package com.Shriyamlearnstocode.JPALearning.Dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
		//Session session= entityManager.unwrap(Session.class);
		//Query<InstructorMan> ql= session.createQuery("Delete From InstructorMan  Where id= :theid",InstructorMan.class);
		//Query<InstructorMan> ql=session.createQuery("Delete From InstructorMan Where id = :theid");
//Note:when writting jqpl query for deleting and updating then query will only contain only
		//the query and not the class name 
		

	//	ql.setParameter("theid", theId).executeUpdate();
		entityManager.createQuery("Delete (InstructorMan I, I.instructorDetail Idt) From InstructorMan I Join InstructorMan.instructorDetail Idt Where id= :theid").setParameter("theid", theId).
		executeUpdate();
		//ql.setParameter("theid", theId);
	//	return ql.executeUpdate();
	//note that this will also return instructordetail with it also
	
	}


}
