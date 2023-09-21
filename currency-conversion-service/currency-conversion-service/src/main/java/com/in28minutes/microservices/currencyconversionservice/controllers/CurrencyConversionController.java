  package com.in28minutes.microservices.currencyconversionservice.controllers;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.in28minutes.microservices.currencyconversionservice.entity.CurrencyConversion;
import com.in28minutes.microservices.currencyconversionservice.entity.CurrencyExchangeProxy;

@Configuration(proxyBeanMethods=false)
class RestTemplateConfiguration{
//we need to create resttemplate like this, and then inject it in the controller class to enable micrometer(in zipkin dts) to trace the request
	//going from this class to another service by the use of  resttemplate object's function getForEntity(), just like we have to use feign-micrometer dependency to enable dts to trace
//request going on to another service via feign
	@Bean
	RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}

@RestController
public class CurrencyConversionController {
	@Autowired
	CurrencyExchangeProxy proxy;
	
	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
	//getForEntity() :Retrieve an entity by doing a GET on the specified URL.The response is converted and stored in a ResponseEntity. 
		
		HashMap<String,String> uriVariables= new HashMap<>();
		uriVariables.put("from",from);//from is the key and and next from is its value
		uriVariables.put("to",to);//to is the key and and next to is its value
		//ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://www.localhost:8000/currency-exchange/from/{from}/to/{to}",
			//	CurrencyConversion.class,uriVariables);//note that when you are calling another api service from within a api then before 
// calling that  api first ensure that the called api is already running
//also note that if we directly use object of resttemplate()(ie new RestTemplate()) then we would not be able to trace the request going to the
//other service, so we would need to create the resttemplate object and inect it in   certain way which we have done above this class
		//ResponseEntity<CurrencyConversion> responseEntity = restTemplate.getForEntity("http://www.localhost:8000/currency-exchange/from/{from}/to/{to}",
			//		CurrencyConversion.class,uriVariables);
		ResponseEntity<CurrencyConversion> responseEntity = restTemplate.getForEntity("http://currency-exchange:8000/currency-exchange/from/{from}/to/{to}",
				CurrencyConversion.class,uriVariables);
			CurrencyConversion currencyConversion=responseEntity.getBody();

			
		//note: new RestTemplate() is used to call an another api service, 
//note that the values of the variables of the entity that will be retrieved from the specified url will get mapped to the variables of the 
// specified class, so id gets mapped to id and so on and so note that the name of the variables of the entity being called and the name of
//variables of the speicfied class (to which the values will get mapped) should be exact same
	//we are using the uriVariables to supply the value of from and to, to the specified url 	
		return new CurrencyConversion(currencyConversion.getId(), from, to, quantity,currencyConversion.getConversionMultiple(),
				quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment());
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
//when we create a docker container for the currency-conversion then only this url will work to run the service(and not the above one) bcz
//this method uses feign and feign will pick the currency-exchange service from the eureka naming server whereas the above method does not uses
//feign and directly calls the currency-exchange service using its url and as we know that container does not takes the line "localhost:8000"
//in the same way as our machine instead container will start searching for a localhost:8000 inside the container
	public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
	
		
		
			CurrencyConversion currencyConversion= proxy.retrieveExchangeValue(from, to);//calling the retrieveExchangeValue() method will 
//return a CurrencyConversion object and that CurrencyConversion object my friend is the one to which all the values of CurrencyExchange object
		//is mapped

			
		
		return new CurrencyConversion(currencyConversion.getId(), from, to, quantity,currencyConversion.getConversionMultiple(),
				quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment());
	}
}
