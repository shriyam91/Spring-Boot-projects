package com.Shriyamlearnstocode.JPALearning;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.Shriyamlearnstocode.JPALearning.Dao.OneToOneDao;
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
		  deleteInstructor(oneToOneDao);
	};
}
private void deleteInstructor(OneToOneDao oneToOneDao) {
	//for(int theid=3;theid<=10;theid++) {
		
	int theid= 16;
	oneToOneDao.deleteInstructorById(theid);
	System.out.println("deletion done");
	}
		
	//}

private void findInstructor(OneToOneDao oneToOneDao) {
		int theid= 1;
		InstructorMan instFound= oneToOneDao.findInstructorById(theid);
		System.out.println("instructor is  "+instFound +" and his details are "+ instFound.getInstructorDetail());
		
	}

private void createInstructor(OneToOneDao oneToOneDao) {
//	//Instructor in1= new Instructor("Vishal", "Dadlaani","vishalcode@gmail.com");
//	InstructorMan temp= new InstructorMan("Vishal","Bharadwaaj","VishalTheDirector@gmail.com");
//	InstructorDetail ind1= new InstructorDetail("http://www.youtube.com/vishalmusic","music");
//	temp.setInstructorDetail(ind1);
//	oneToOneDao.save(temp);
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
////	
	
	InstructorDetail tempInstdet2= new InstructorDetail("http://www.youtube.com/Selinium Express","coding");
////	
	tempinst2.setInstructorDetail(tempInstdet2);
	oneToOneDao.save(tempinst2);
	System.out.println("saved,  Done!");
	//note that first instructordetail is saved and then instructor bcz only when 1st instructordetail
	// will be saved then its id can be saved as in the instructor_detail_id field of the instructor
	//table
}
}
