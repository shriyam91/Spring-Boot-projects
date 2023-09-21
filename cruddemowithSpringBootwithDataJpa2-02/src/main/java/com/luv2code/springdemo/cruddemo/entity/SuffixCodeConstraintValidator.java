package com.luv2code.springdemo.cruddemo.entity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SuffixCodeConstraintValidator implements ConstraintValidator<SuffixCode, String>{
	
	private String suffix;
	@Override
	public void initialize(SuffixCode theCode) {
		// TODO Auto-generated method stub
		//suffix= theCode.value();
				
	}

	@Override
	public boolean isValid(String userVal, ConstraintValidatorContext context) {
		
		if(userVal!=null){
			return userVal.endsWith(".com");
		}
		
		
		return false;
	

}
	}

