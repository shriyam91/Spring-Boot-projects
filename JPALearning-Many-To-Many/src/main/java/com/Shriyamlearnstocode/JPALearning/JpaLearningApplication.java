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
import com.Shriyamlearnstocode.JPALearning.Entity.Review;
import com.Shriyamlearnstocode.JPALearning.Entity.Student;

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
		//createInstructorWithCoureses(oneToOneDao);
		//  findInstructorWithCourses(oneToOneDao);
		  //findCoursesForInstructor(oneToOneDao);
		// findInstructorWithCoursesJoinFetch(oneToOneDao);
		//	deleteCourseAndItsReviewsById(oneToOneDao);
		//createCoursesWithReviews(oneToOneDao);
		//	deleteInstructorCourseAndItsReviewsById(oneToOneDao);
		// createCoursesAndStudents(oneToOneDao);
		findCourseAndStudent(oneToOneDao);
		
	};
}
	

private void findCourseAndStudent(OneToOneDao oneToOneDao) {
	int Theid=10;
	Course cs=oneToOneDao.findCourseAndStudentByCourseId(Theid);
	System.out.println("courses are "+ cs);
	System.out.println("students are "+ cs.getStudents());
	
	
	
	
}

private void createCoursesAndStudents(OneToOneDao oneToOneDao) {
	Course tempCourse= new Course("Economy by Amit Singh");
	Student s1= new Student("Rishi","Gulati","Rishi@gamil.com");
	Student s2= new Student("Mahesh","Bhoopati","Mahesh@gamil.com");
	//add students to the course
	tempCourse.addStudent(s1);
	tempCourse.addStudent(s2);
//note: here simply adding the method will generate the fk feild value and there is no need to call the setter
//same as in onetomany uniD bcz jo bhi table owns the responsibility of the fk then us table ke us wale field
//me (jo field doosre class ki object hai jiske sath onetomany unid ka relation h) add karne matr se fk field
//ki value generate/update ho jati hai
//onetomanybi directional me instructorMan is not the entity table responsible for the fk as per the jpa rules
//so us entity ke field me(jo field doosre entity ka object hai) me value generate karne se fk value generate
//nahi hogi tabhi hume setter method bhi cal karna padta hai using the object of the entity that is responsible
//for the fk(and there it was course class)
//for jpa2.0 rules on table relations jpa bible is :wikibooks.org/wiki/Java_Persistence
	System.out.println("when we save the course then students will also be saved with it and vice versa bcz "
			+ "tables have fk");
	oneToOneDao.saveCourse(tempCourse);
	System.out.println("courses and the enrolled students saved");
	
}

private void deleteInstructorCourseAndItsReviewsById(OneToOneDao oneToOneDao) {
//note:in onetomanybidierectional first we need to break the link b/w 2 tables and then delete data from the
//child table ie table without fk and then delte data from the  parent table ie table with fk 
	int theId=2;
	//first lets get all the courses of for the instructor to be deleted b4 it gets deleted
	List<Course> courses= oneToOneDao.findCoursesByInstructorId(theId);
	//	//first deleting instructor only
	oneToOneDao.deleteInstructorById(theId);
	//now deleting the courses will also delete the reviews bcz
	for(Course cc: courses) {
//in onetomanyuniD since table child table ie table without the fk column is responsible for the fk and updating 
		//its values in the fk field so deleting the data from the table without the fk field will delete the
	//data from both tables simply bcz table without the fk field is responsible for the fk
		oneToOneDao.deleteCourseById(cc.getId());
	}
	
}

private void createCoursesWithReviews(OneToOneDao oneToOneDao) {
//	Course tempCourse = new Course("Political Science from plato till Current Foreign Relations ");
//	
//	Review r1= new Review("indepth notes but teaching speed too fast");
//	Review r2= new Review("Greate way of teaching");
//	tempCourse.addReview(r1);
//	tempCourse.addReview(r2);//note:adding the course to the review list will automaticallly update fk field 
	//ie course_id bcz here course has a onetomanyunidirectional mapping with the review field and in onetoamny
	//unidrctional mapping source table is the ownner of the foreign key and is reposible for updating the fk
	//field and that is why we have not used the review.setCourse method inside the addReview method
	
	//oneToOneDao.saveCourse(tempCourse);
	//note that we cannot create reviews for the already created course 
	
}

private void deleteCourseAndItsReviewsById(OneToOneDao oneToOneDao) {
	int theid=10;
//note:here in onetomany unidirectional we can delete the course directly without having to do first: 
	//review.setCourse(null) bcz since in onetomany uniD source entity is the owner of the fk and is responsible
	//for updating the values in the fk field so deleting the source entity will also delete the reviews
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
	//now we will create instructor, instructordetail, courses for a instructor, reviews for a course all together
	InstructorMan temp2= new InstructorMan("Jatin","Sir","Jatin@gmail.com");
	InstructorDetail ind2= new InstructorDetail("http://www.youtube.com/UPSCExpress","IAS with jatin");	
	temp2.setInstructorDetail(ind2);
	
	Course tempCourse3 = new Course(" upsc prelims in 6 months ");
	Course tempCourse4= new Course("Comple current affairs ");
// add courses to instructor
			temp2.add(tempCourse3);
			temp2.add(tempCourse4);
			Review r1= new Review("best  teacher available online");
			
			Review r2= new Review("Greate way of teaching, learning with fun");
			tempCourse3.addReview(r1);
			tempCourse3.addReview(r2);
			
	//tempCourse3.setInstructor(temp2);
	//tempCourse4.setInstructor(temp2);//note that this calling setInstructor method separately( and not first
//adding the courses to the courses list in the instructorman class and tthen calling the setinstructor method
//through that added course inthe course list) will surely set the instructor for the course but when we will
//go on saving the instructor then it will not only save the instructor and the instructor detail and not the
//course bcz Since the instructorman has build manytone relation with the instructorman using the field 'list courses'
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
