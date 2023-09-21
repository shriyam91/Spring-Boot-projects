package com.in28minutes.microservices.currencyexchangeservice.controllers;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.currencyexchangeservice.entity.CurrencyExchange;
import com.in28minutes.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
		
	@Autowired
	private Environment environment;//note:always import Environment from core.env
	 
	private Logger logger= LoggerFactory.getLogger(CurrencyExchangeController.class);
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		logger.info("retriveExchangeValue called with {} to {}",from ,to);
		
		
		//CurrencyExchange currencyExchange= new 	CurrencyExchange(1000L, from, to, BigDecimal.valueOf(55));
		
		CurrencyExchange currencyExchange= repository.findByFromAndTo(from, to);
		if(currencyExchange==null) {
			throw new RuntimeException("Unable to find data for "+from+" and "+to);
		}
		String port =environment.getProperty("local.server.port");//this will get the current port number and store it in port variable as string
		currencyExchange.setEnvironment(port);
		
		
		return currencyExchange;
	}
}
