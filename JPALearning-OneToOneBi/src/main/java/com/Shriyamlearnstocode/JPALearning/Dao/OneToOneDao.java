package com.Shriyamlearnstocode.JPALearning.Dao;

import java.util.List;

import com.Shriyamlearnstocode.JPALearning.Entity.InstructorDetail;
import com.Shriyamlearnstocode.JPALearning.Entity.InstructorMan;

public interface OneToOneDao {
	public InstructorMan save(InstructorMan theInstructor);
	public InstructorMan findInstructorById(int theId);
	public void deleteInstructorById(int theId);
	public InstructorDetail findInstructorDetailById(int theId);
	public void deleteInstructorDetailById(int theId);
	public List<Object[]> findInstructorByHobby(String hobby);
	public void updateInstructorDetailByHobby(String firstName, String LastName);
	public void updateInstructorManByFirstNameAndLastName(String firstName, String lastName);
	public void updateInstructorManByHobby(String theHobby);
	public List<Object[]> selectingFromBothTableUsingJoin();
}
