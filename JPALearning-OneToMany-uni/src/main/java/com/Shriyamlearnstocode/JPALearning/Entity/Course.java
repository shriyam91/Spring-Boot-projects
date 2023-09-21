package com.Shriyamlearnstocode.JPALearning.Entity;





import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
//the goto bible for springdatajpa is wikibooks.org/wiki/Java_Persistence/ 
@Entity
@Table(name="course")
public class Course {

	// define our fields
	
	// define constructors
	
	// define getter setters
	
	// define tostring
	
	// annotate fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	  
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="instructor_id")
	private InstructorMan instructorMan;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="course_id")

	List<Review> reviews;
	// In JPA 2.x a @JoinColumn can be used on a OneToMany to define the foreign key, some JPA providers may support this already.
	//The main issue with an unidirectional OneToMany is that the foreign key is owned by the target object's table,
	//so if the target object has no knowledge of this foreign key, inserting and updating the value is difficult.
	//In a unidirectional OneToMany the source object take ownership of the foreign key field, and is responsible for updating its value.
	//The target object in a unidirectional OneToMany is an independent object, so it should not rely on the foreign key in any way
	//note:since relationship b/w course and reviews is onetomanyunidirectional and in onetomanyunidirectional
	//relationship source object takes the reponsibility of the foreign key and is reponsible for updating
	//the value of the fk field , so here in course class we will use the @joincolumn annotation(name="fkfeildname")
	//upon the reviews field and since in onetomanyunidirectional source class is only reponsible for updating 
	//the value of the fk field,
	//so source column insert the values in the field course_id as we keep on adding the reviews in the review
	//list and we do not need to call the coursesetter method(using the review object) to insert the values
	//in the course_id field(which we have to do in onetomanybidirectional like in instructor and course class)
	
	
	//IN SHORTCUT SIMPLE LANGUAGE:
	//IN ONETOMANY-MANYTOONE Bidirectional relationship:
		//-@JoinColumn(name="fkfieldname") is used in the target entity
		//-target entity is the source entity class
		//-FK column is the target entity table 
	//to update the values in the fk field of the target entity we need to call the setter methods
	//IN ONETOMANY-Unidirectional relationship:
			//-@JoinColumn(name="fkfieldname") is used in the Source entity
			//-Source entity is the owner entity class
			//-FK column is the target entity table 
	//we do not need to call the setter methods to update values in the fk field 
	
	
	public Course() {
		
	}

	public Course(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public InstructorMan getInstructor() {
		return instructorMan;
	}

	public void setInstructor(InstructorMan instructorMan) {
		this.instructorMan = instructorMan;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
		
	}
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public void addReview(Review review) {
		if(reviews== null) {
			
			reviews= new ArrayList<>();
		}
		reviews.add(review);
	

	}
	
	
}
