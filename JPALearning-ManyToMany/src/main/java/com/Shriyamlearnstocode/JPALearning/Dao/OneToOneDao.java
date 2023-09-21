package com.Shriyamlearnstocode.JPALearning.Dao;

import com.Shriyamlearnstocode.JPALearning.Entity.InstructorMan;

public interface OneToOneDao {
	public InstructorMan save(InstructorMan theInstructor);
	public InstructorMan findInstructorById(int theId);
	public void deleteInstructorById(int theId);
}
