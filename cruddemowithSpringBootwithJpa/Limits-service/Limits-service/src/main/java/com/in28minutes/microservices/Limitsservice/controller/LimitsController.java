package com.in28minutes.microservices.Limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.Limitsservice.configuration.Configuration;
import com.in28minutes.microservices.Limitsservice.controller.beans.Limits;

@RestController
public class LimitsController {
	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public Limits retreiveLimits() {
		
		return new Limits(configuration.getMinimum(),configuration.getMaximum());
	}
}
