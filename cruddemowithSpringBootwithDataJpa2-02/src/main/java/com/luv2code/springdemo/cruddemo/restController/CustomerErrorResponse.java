package com.luv2code.springdemo.cruddemo.restController;

public class CustomerErrorResponse {
	String message;
	String Status ;
	Long timeStamp;
	
	
	
	public CustomerErrorResponse() {
		
	}
	public CustomerErrorResponse(String message, String status, Long timeStamp) {
		
		this.message = message;
		Status = status;
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	

}
