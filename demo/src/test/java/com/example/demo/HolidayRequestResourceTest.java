package com.example.demo;

import com.example.starter.HollidayExceptionConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import(HollidayExceptionConfiguration.class)
//@HollidayWebMvcTest
class HolidayRequestResourceTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	void shouldReturn200ForBeltrano() throws Exception {
		
		mockMvc.perform(post("/")
				                .contentType(APPLICATION_JSON)
				                .content(getWriteValueAsString(beltrano())))
		       .andDo(MockMvcResultHandlers.print())
		       .andExpect(status().isOk());
		
	}
	
	@Test
	void shouldReturn400ForFulano() throws Exception {
		
		mockMvc.perform(post("/")
				                .contentType(APPLICATION_JSON)
				                .content(getWriteValueAsString(fulano())))
		       .andDo(MockMvcResultHandlers.print())
		       .andExpect(status().isBadRequest())
		       .andExpect(jsonPath("$.cause").value("Beltrano possui ferias no mesmo periodo"));
	}
	
	private HolidayRequest fulano() {
		return new HolidayRequest("Fulano", LocalDate.now().plusDays(1L), 40);
	}
	
	private HolidayRequest beltrano() {
		return new HolidayRequest("Beltrano", LocalDate.now().plusDays(1L), 40);
	}
	
	private static String getWriteValueAsString(HolidayRequest holidayRequest) throws JsonProcessingException {
		return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(holidayRequest);
	}
	
}