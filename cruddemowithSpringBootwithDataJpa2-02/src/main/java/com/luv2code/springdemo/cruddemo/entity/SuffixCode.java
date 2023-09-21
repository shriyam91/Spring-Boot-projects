package com.luv2code.springdemo.cruddemo.entity;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import jakarta.servlet.annotation.HttpConstraint;
@Constraint( validatedBy= SuffixCodeConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
//In target annotation we simply mention on what elements can this custom annotation can be
//applied, so it can be applied on fields and on methods
@Target( {ElementType.FIELD, ElementType.METHOD})
public @interface SuffixCode {

	public String value() default "@gmail.com";
	
	public String message() default "every email should end with @gmail.com";
	
	//define default groups
	public Class<?>[] groups() default{};
	 //payload() method will contain the custom/additional details about the validation failure
	
	public Class<? extends Payload>[] payloads() default{};
	
}
