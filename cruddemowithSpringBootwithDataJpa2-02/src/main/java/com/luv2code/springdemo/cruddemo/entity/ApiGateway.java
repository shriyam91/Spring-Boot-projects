package com.luv2code.springdemo.cruddemo.entity;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGateway {
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		
		return builder.routes().route(p-> p.path("/currency-exchange/facebook")//if a request is coming to this uri path
						.uri("/facebook.com"))//then talk to the eureka naming server and loadbalance this service from the eureka server 
		
		.build();
		
	}

}
