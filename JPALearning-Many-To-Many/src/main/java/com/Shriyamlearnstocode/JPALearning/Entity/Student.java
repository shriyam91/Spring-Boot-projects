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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;




@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	//note we will always use GenerationType as IDENTITY whenever we have made id column in sql table on autoincrement
	//if we do not set GenerationType as IDENTITY then we will not be able to retrieve,update data using id(the primary key) via session.get(Student.class,studentobject.getId()
	@Column(name="first_name")
	 private String firstName;
	@Column(name="last_name")
	private String lastName;
	private String email;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade= {CascadeType.DETACH, CascadeType.MERGE ,
			CascadeType.PERSIST, CascadeType.REFRESH}) 
	@JoinTable(name="course_student", joinColumns=@JoinColumn(name="student_id"),
	inverseJoinColumns=@JoinColumn(name="course_id"))
	
	private List<Course> courses;
	
	Student(){
		
	}

	public Student(String firstName, String lastName, String email) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
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

	public void setCourses(List<Course> course) {
		this.courses = course;
	}
	
	public void addCourse(Course crs) {
		if(courses==null) {
			courses= new ArrayList<>();
		}
		
		courses.add(crs);
	}

	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
	

}
