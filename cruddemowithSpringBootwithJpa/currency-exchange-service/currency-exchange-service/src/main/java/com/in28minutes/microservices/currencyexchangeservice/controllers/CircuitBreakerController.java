package com.in28minutes.microservices.currencyexchangeservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {
	
	private Logger logger= LoggerFactory.getLogger(CircuitBreakerController.class);
	
	//@Retry(name="default",fallbackMethod= "hardcodedResponse")//if there is a failure in getting the response back on calling this api, @Retry annotation will 
	//enable retry 3 times(bcz default value is 3)and only if all 3 try fails to get back response then only error page will be returned
	@GetMapping("/sample-api")
	//@CircuitBreaker(name="default",fallbackMethod= "hardcodedResponse")//circuitbreaker calls a ms a few times and if it is down then it stops
	//calling it( inspite of calls being made to it) and returns the fallback response directly 
	@RateLimiter(name="default")//ratelimiter is used to fix a specific no of calls to a api within a specific period of time ex:10 calls within
	// 10 seconds and more calls than the fixed no will give error, and we will make the configurtion for ratelimiter in properties file
	public String sampleApi() {
	//	
		logger.info("Sample api received");//since the below api will not run so this msg will be printed as many times as set in the retry attribute
		
	//ResponseEntity<String> entity=	new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
		//return entity.getBody();
		return "sampleApi";
	}
	public String hardcodedResponse(Exception ex) {
		return "fallback-response";
	}
}
