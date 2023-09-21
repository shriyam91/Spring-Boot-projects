package com.Shriyamlearnstocode.JPALearning.Dao;

import com.Shriyamlearnstocode.JPALearning.Entity.InstructorDetail;
import com.Shriyamlearnstocode.JPALearning.Entity.InstructorMan;

public interface OneToOneDao {
	public InstructorMan save(InstructorMan theInstructor);
	public InstructorMan findInstructorById(int theId);
	public void deleteInstructorById(int theId);
	public InstructorDetail findInstructorDetailById(int theId);
	public void deleteInstructorDetailById(int theId);
}
