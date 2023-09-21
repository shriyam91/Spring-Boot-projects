package com.Shriyamlearnstocode.JPALearning;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.Shriyamlearnstocode.JPALearning.Dao.OneToOneDao;
import com.Shriyamlearnstocode.JPALearning.Entity.Course;
import com.Shriyamlearnstocode.JPALearning.Entity.InstructorDetail;
import com.Shriyamlearnstocode.JPALearning.Entity.InstructorMan;

@SpringBootApplication
public class JpaLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaLearningApplication.class, args);
	}
	
@Bean
public CommandLineRunner commandLineRunner(OneToOneDao oneToOneDao) {
	return runner-> {
////		
		//createInstructor(oneToOneDao);
		//findInstructor(oneToOneDao);
	//	deleteInstructor(oneToOneDao);
		//findInstructorDetail(oneToOneDao);
	//	deleteInstructorDetail(oneToOneDao);
		//findInstructorByHobby(oneToOneDao);
		//updateInstructorDetailByHobby(oneToOneDao);
		//updateInstructorByHobby(oneToOneDao);
		//updateInstructorManByFirstNameAndLastName(oneToOneDao);
		//selectingFromBothTableUsingJoin(oneToOneDao);
		// createInstructorWithCoureses(oneToOneDao);
		//  findInstructorWithCourses(oneToOneDao);
		  //findCoursesForInstructor(oneToOneDao);
		// findInstructorWithCoursesJoinFetch(oneToOneDao);
		deleteCourseById(oneToOneDao);
	};
}
	

private void deleteCourseById(OneToOneDao oneToOneDao) {
	int theid=12;
	oneToOneDao.deleteCourseById(theid);
	
}

private void findInstructorWithCoursesJoinFetch(OneToOneDao oneToOneDao) {int theId=11;
InstructorMan im=oneToOneDao.findInstructorById(theId);
System.out.println(im+" and courses are "+im.getCourses());
System.out.println("done");
	
}

private void findCoursesForInstructor(OneToOneDao oneToOneDao) {
	// TODO Auto-generated method stub
	int theId=11;
	InstructorMan im=oneToOneDao.findInstructorById(theId);
	System.out.println(im);
	List<Course> cc= oneToOneDao.findCoursesByInstructorId(theId);
	for(Course c : cc) {
	System.out.println(c);
	}
}

private void findInstructorWithCourses(OneToOneDao oneToOneDao) {
	// TODO Auto-generated method stub
	int theId=11;
	InstructorMan im=oneToOneDao.findInstructorById(theId);
	System.out.println(im);

	
}

private void createInstructorWithCoureses(OneToOneDao oneToOneDao) {
//	InstructorMan temp= new InstructorMan("Vishal","Dadlaani","VishalSindhMusian@gmail.com");
//	InstructorDetail ind1= new InstructorDetail("http://www.youtube.com/vishalmusic","musicproduction");	
//	temp.setInstructorDetail(ind1);//only when we calll the setter method
//	Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
//	Course tempCourse2 = new Course("The sargaml course");
//	//now adding the courses and seting the intructor for the courses with the setter method inside the add method 
//	//with this method inside instructorman class:
//	temp.add(tempCourse1);
//	temp.add(tempCourse2);
//	oneToOneDao.save(temp);
	
	InstructorMan temp2= new InstructorMan("Nidhi","Ma'am","NidhiHindi@gmail.com");
	InstructorDetail ind2= new InstructorDetail("http://www.youtube.com/HindiExpress","HindiWithNidhi");	
	temp2.setInstructorDetail(ind2);
	
	Course tempCourse3 = new Course("Sampoorn hindi lekhpal");
	Course tempCourse4= new Course("nibandh evam apathit gadyaansh");
// add courses to instructor
			temp2.add(tempCourse3);
			temp2.add(tempCourse4);
	//tempCourse3.setInstructor(temp2);
	//tempCourse4.setInstructor(temp2);//note that this calling setInstructor method separately( and not first
//adding the courses to the courses list in the instructorman class and tthen calling the setinstructor method
//through that added course inthe courses list) will surely set the instructor for the course but when we will
//go on saving the instructor then it will  only save the instructor and the instructor detail and not the
//course bcz Since the instructorman has build manytone relation with the course using the field 'list courses'
//so only those courses that are added inside this list will be linked to the instructorman class(when they 
//call the setter method) and not any random course i create and call the instructorman setter method on it
	
	
	 oneToOneDao.save(temp2);
	

	System.out.println("saved,  Done!");

	
}

private void updateInstructorByHobby(OneToOneDao oneToOneDao) {
	String hb="Weightlifting and health";
	oneToOneDao.updateInstructorManByHobby(hb);
	System.out.println("successfull updation");
	
}

private void updateInstructorDetailByHobby(OneToOneDao oneToOneDao) {
		oneToOneDao.updateInstructorDetailByHobby("Vishal","Bharadwaaj");
		System.out.println("successfull updation");
	
}

private void updateInstructorManByFirstNameAndLastName(OneToOneDao oneToOneDao) {
	oneToOneDao.updateInstructorManByFirstNameAndLastName("Abhinav", "Mahajan");
	System.out.println("successfull updation");

}

private void findInstructorByHobby(OneToOneDao oneToOneDao) {
	String hb= "coding";
	List<Object[]> hobbies= oneToOneDao.findInstructorByHobby(hb);
	for(Object[] arr: hobbies) {
		System.out.println(Arrays.toString(arr));
	}
	
}

private void selectingFromBothTableUsingJoin(OneToOneDao oneToOneDao) {
	
	List<Object[]> hobbies= oneToOneDao.selectingFromBothTableUsingJoin();
	for(Object[] arr: hobbies) {
		System.out.println(Arrays.toString(arr));
	}
	
}

private void findInstructorDetail(OneToOneDao oneToOneDao) {
		int theid= 1;
		InstructorDetail instdetFound= oneToOneDao.findInstructorDetailById(theid);
		System.out.println("instructordetail is  "+instdetFound +" and his instructor is "+ instdetFound.getInstructorMan());
		
	}
private void deleteInstructorDetail(OneToOneDao oneToOneDao) {
	//for(int theid=3;theid<=10;theid++) {
		
	int theid= 2;
	oneToOneDao.deleteInstructorDetailById(theid);
	System.out.println("deletion done");
	}
private void deleteInstructor(OneToOneDao oneToOneDao) {
	//note: since instructor is having the onetomany relation with the course and instructor is the child
//table and the course is the parent table(since fk is in the course table) so we cannot delete the instructor either without:
	//without deleting the course(deleting the course will also delete the referenced instructor and if we only
	//want to delete the instructor without deleting the courses then we just need to break the fk reference b/w
	// the parent and the child table: we will do it in the dao implementation class
	int theId= 16;
	oneToOneDao.deleteInstructorById(theId);
	System.out.println("deletion done");
	
		
	}

private void findInstructor(OneToOneDao oneToOneDao) {
		int theid= 1;
		InstructorMan instFound= oneToOneDao.findInstructorById(theid);
		System.out.println("instructor is  "+instFound +" and his details are "+ instFound.getInstructorDetail());
		
	}

private void createInstructor(OneToOneDao oneToOneDao) {
//Instructor in1= new Instructor("Vishal", "Dadlaani","vishalcode@gmail.com");
	InstructorMan temp= new InstructorMan("Vishal","Bharadwaaj","VishalTheDirector@gmail.com");
	InstructorDetail ind1= new InstructorDetail("http://www.youtube.com/vishalmusic","music");	temp.setInstructorDetail(ind1);
	temp.setInstructorDetail(ind1);
	oneToOneDao.save(temp);
//	//since we have applied CascadeType.All so when we save the instructor then instructor
//	//detail also gets saved and the primary key 
//	
//	System.out.println("new instructor with id "+ ind1.getId()+ " is saved and the corresponding instructor detial is "
//			+ "also saved" );
////these are the old ones	
	InstructorMan tempinst= new InstructorMan("Chad", "Darby", "luv2code@gmail.com");
////	
	InstructorDetail tempInstdet= new InstructorDetail("http://www.luv2code.com/youtube","coding");
////    
	tempinst.setInstructorDetail(tempInstdet);
	oneToOneDao.save(tempinst);
	InstructorMan tempinst2= new InstructorMan("Abhinav", "Bengali", "Abhinav@gmail.com");
//////	
	InstructorDetail tempInstdet2= new InstructorDetail("http://www.youtube.com/Selinium Express","coding");
//////	
	tempinst2.setInstructorDetail(tempInstdet2);
	oneToOneDao.save(tempinst2);
InstructorMan tempinst3= new InstructorMan("Abhinav", "Mahajan", "AbhinavTrainer@gmail.com");
//////
InstructorDetail tempInstdet3= new InstructorDetail("http://www.youtube.com/BeFit","Weightlifting and health");
//////
tempinst3.setInstructorDetail(tempInstdet3);
oneToOneDao.save(tempinst3);
	InstructorMan tempinst4= new InstructorMan("Chota", "Chattri", "ChotaChatri@gmail.com");
	InstructorDetail tempInstdet4= new InstructorDetail("http://www.youtube.com/Badmaass","Wasooli ewam aphaaran");
//////
tempinst4.setInstructorDetail(tempInstdet4);
oneToOneDao.save(tempinst4);

	System.out.println("saved,  Done!");
	//note that first instructordetail is saved and then instructor bcz only when 1st instructordetail
	// will be saved then its id can be saved as in the instructor_detail_id field of the instructor
	//table
}
}
