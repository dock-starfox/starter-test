package com.example.starter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HollidayControllerAdvice {
	
	record ExceptionResponse(String cause) {
	
	}
	
	@ExceptionHandler(ConflictHollidayException.class)
	public ResponseEntity<ExceptionResponse> vaiTrabalharException(ConflictHollidayException conflictHollidayException) {
		return ResponseEntity.badRequest().body(new ExceptionResponse(conflictHollidayException.getMessage()));
	}
	
}
