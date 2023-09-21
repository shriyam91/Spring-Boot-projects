package com.Shriyamlearnstocode.JPALearning.Entity;

import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="instructor")
public class InstructorMan {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	int id;
	@Column(name="first_name")
	String firstName;
	@Column(name="last_name")
	String lastName;
	@Column(name="email")
	String email;
	
	@OneToOne(cascade=CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name="instructor_detail_id")
	InstructorDetail instructorDetail;
//note: we will set the instructorDeatil field of the instructor class using the setter method,
//so we will not pass it as a parameter in the constructor
	
		@OneToMany(mappedBy="instructorMan", fetch=FetchType.EAGER, 
				   cascade= {CascadeType.PERSIST, CascadeType.MERGE,
							 CascadeType.DETACH, CascadeType.REFRESH})
	//	@OneToMany(mappedBy="instructor",
		//   cascade= CascadeType.ALL)//note:default fetch type for onetomany is lazy, so when we will try
//to fetch instructor and then print its courses using the instructor object then it will give error bcz 
//default fetch type is eager so it meaans it wont fetch the courses with the instructor so we have to 
//set the fetch type to eager if we want that with instructor the courses should also be fetched
		private List<Course> courses;

	public InstructorMan(String firstName, String lastName, String email) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	public InstructorMan() {
	
}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	public InstructorDetail getInstructorDetail() {
		return instructorDetail;
	}
	public void setInstructorDetail(InstructorDetail instructorDetail) {
		this.instructorDetail = instructorDetail;
	}
	@Override
	public String toString() {
		return "InstructorMan [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", instructorDetail=" + instructorDetail + "]";
	}
	
	// add convenience methods for bi-directional relationship
	
		public void add(Course tempCourse) {
			
			if (courses == null) {
				courses = new ArrayList<>();
			}
			
			courses.add(tempCourse);//here we are adding the new course to the list courses of type course
			tempCourse.setInstructor(this);//here this will pass the current instructor object,ie the object
	// that has called this add method, and since we have created FK(that maps Instructor class and 
	//course class) inside the Course table so we need to set the instructor for the course using 
	//the course object only, doing it otherwise ie setting instructor for the course using the 
	//instructor object will not create the FK ID inside the FK lcolumn ie altough instructor will be set but
	// instructor_id column ie the FK column will have value as null
			
		}
	

}
