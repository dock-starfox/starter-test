package com.example.demo;

import com.example.starter.ConflictHollidayException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class HolidayRequestResource {
	
	@PostMapping
	public ResponseEntity<HolidayRequest> createRequest(@Valid @RequestBody HolidayRequest holidayRequest) {
		log.info(holidayRequest.toString());
		
		if (holidayRequest.employeeName().equals("Fulano")){
			throw new ConflictHollidayException("Beltrano possui ferias no mesmo periodo");
		}
		
		return ResponseEntity.ok(holidayRequest);
	}
	
}
