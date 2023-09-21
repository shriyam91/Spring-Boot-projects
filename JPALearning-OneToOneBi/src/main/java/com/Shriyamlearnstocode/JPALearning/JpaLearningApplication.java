package com.Shriyamlearnstocode.JPALearning;

import java.util.Arrays;
import java.util.List;

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
		//deleteInstructor(oneToOneDao);
		//findInstructorDetail(oneToOneDao);
	//	deleteInstructorDetail(oneToOneDao);
		//findInstructorByHobby(oneToOneDao);
		//updateInstructorDetailByHobby(oneToOneDao);
		updateInstructorByHobby(oneToOneDao);
		//updateInstructorManByFirstNameAndLastName(oneToOneDao);
		selectingFromBothTableUsingJoin(oneToOneDao);
	};
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
	//for(int theid=3;theid<=10;theid++) {
		
	int theid= 4;
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
