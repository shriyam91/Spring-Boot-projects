package com.in28minutes.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
		
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		
		return builder.routes().route(p -> p.path("/get").filters(f -> 
		f.addRequestHeader("MyHeader","MyUri ").addRequestParameter("Param","MyValue")).uri("http://httpbin.org:80"))
		.route(p-> p.path("/currency-exchange/**")//if a request is coming to this uri path
						.uri("lb://currency-exchange"))//then talk to the eureka naming server and loadbalance this service from the eureka server 
		.route(p-> p.path("/currency-conversion/**")//if a request is coming to this uri path ie currency-conversion followed by any thing 
				.uri("lb://currency-conversion"))//then talk to the naming server then redirect it to the exact url of this service
		.route(p-> p.path("/currency-conversion-feign/**")//if a request is coming to this uri path
				.uri("lb://currency-conversion"))
		
		.route(p-> p.path("/currency-conversion-new/**")//if we mention any wrong url like here and we want still want to hit it to the right url then
				.filters(f-> f.rewritePath("currency-conversion-new/(?<segment>.*)", "currency-conversion-feign/{segment}"))//1st arg. the string to be replaced and 2nd arg the string to replace with
		// ?<segment>.* is regex for defining a segment that can by any thing following the 'currency-conversion-new, and {segment} means puting the same segment  as b4 here also		
				.uri("lb://currency-conversion"))
		.build();
		
	}
}
