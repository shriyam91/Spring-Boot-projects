package com.luv2code.springdemo.cruddemo.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CentralExceptionHandler extends ResponseEntityExceptionHandler{
	private CustomerErrorResponse cer;
	
	
@ExceptionHandler
public ResponseEntity<CustomerErrorResponse> customerException(CustomerNotFoundException exc){
	CustomerErrorResponse cer= new CustomerErrorResponse();
	cer.setMessage(exc.getMessage());
	 cer.setStatus(HttpStatus.NOT_FOUND.toString());
	 cer.setTimeStamp(System.currentTimeMillis());
	
	return new ResponseEntity<>(cer, HttpStatus.NOT_FOUND);
}

}
