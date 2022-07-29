package com.example.demo;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record HolidayRequest(
		@NotNull @NotBlank String employeeName,
		@NotNull @Future LocalDate start,
		@NotNull @Min(1) Integer duration) {
	
}