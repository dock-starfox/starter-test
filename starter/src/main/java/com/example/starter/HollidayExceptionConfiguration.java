package com.example.starter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HollidayExceptionConfiguration {
	
	@Bean
	public HollidayControllerAdvice hollidayControllerAdvice(){
		return new HollidayControllerAdvice();
	}
	
}
