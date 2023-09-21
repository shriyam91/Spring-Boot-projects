package com.in28minutes.microservices.currencyconversionservice.entity;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//STEPS for using feign to call another api sercvice
//1st: create an interface that will work as a proxy for the entity that will be retrieved by calling the another api service
//2nd: use the @Feignclient annotation above that interface and specify the name of the api service that has to be called and the name
//should be the same as mentioned in that service's application.properties
// and specify the port on which it is running in the url attribute when you are not using a naming server where all the service are
//registered and if you are using a naming server as eureka then no need to mention the port bcz then feign will pick that service up from the
//eureka naming server.3rd: create a method and use @GetMapping annoation above it and specify
//the url on which the another api service is running and no need to specify the port ie localhost:8000 bcz feign will pick the service up from
// the eureka, this will call and retrieve the values of the CurrencyExchange  
// and will mapp it to the CurrencyConversion object and so it is necessary that the method created here should be of type CurrencyConversion
//4th:Also use @EnableFeignClients annotation in the application class, now in the controller class create a method of type CurrencyConversion
//and specify the url on which you want to run the CurrencyConversion service and then inside it using the object of CurrencyExchangeProxy interface
//call the method created in the CurrencyExchangeProxy and retrieve it in a CurrencyConversion object and then use this object's value and the 
//values  retrieved through pathvariable to return a new ojbect of CurrencyConversion
//5th:to enable tracing request going from this service to currencyexchange service other than micrometer,opentelemetry,zipkin dependency we
//also need feign-micrometer dependency

//From currency-conversion we want to call currency-exchange, so we have created a proxy interface for currency-exchange
//@FeignClient(name="currency-exchange", url="localhost:8000")//@FeignClient is used to call another api service and in the name attribute we 
//specify the name of the service that is mentioned in application.properties's spring.application.name field and then we also specify the url
//on which the called api is running
@FeignClient(name="currency-exchange")//note: we do not specify the url of the application to be called when all
//the services are registered with the eureka naming server and we want feign to ask the naming server for the 
//instance and url of the service to be called,this is also uselful in real projects where if the url of the 
//service to be called changes then we do not need to update that change in the url attribute of the feignclient
//annotation bcz feign is using the naming server to get the url and the instance of the service to be called
public interface CurrencyExchangeProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);//here we are getting mapping from
//currency-exchange service's entity  and the values of the called service's entity  will get mapped to the CurrencyConversion entity since both
//have similar variables

}
