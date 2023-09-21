package com.Shriyamlearnstocode.JPALearning.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Shriyamlearnstocode.JPALearning.Entity.InstructorMan;



@Repository
public interface InstructorManRepository extends JpaRepository<InstructorMan,Integer> {
	
//	@Transactional
//	@Modifying
//	@Query("Delete InstructorMan I And I.instructorDetail Where id = :Theid")
//	public void deleteInstructorManById(int Theid);
	
	@Modifying
	@Transactional
	public void deleteById(int Theid);
	
	

}
