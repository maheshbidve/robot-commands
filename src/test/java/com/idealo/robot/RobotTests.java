package com.idealo.robot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MissingServletRequestParameterException;

import com.idealo.robot.controller.RobotController;

@WebMvcTest(value = RobotController.class)
public class RobotTests {
	

		@Autowired
		private MockMvc mockMvc;

		@MockBean
		RobotController controller;
		
		@Test
		public void test_missing_param() throws Exception {
			mockMvc.perform(MockMvcRequestBuilders.post("/v1/robots/control"))
			.andExpect(status().is4xxClientError())
			.andExpect(result -> assertTrue(
					result.getResolvedException() instanceof MissingServletRequestParameterException))
			.andExpect(result -> assertEquals(
					"Required request parameter 'script' for method parameter type String is not present",
					result.getResolvedException().getMessage()));
	;
		}
		
		@Test
		public void test_successful_param() throws Exception {
			mockMvc.perform(MockMvcRequestBuilders.post("/v1/robots/control").param("script", "POSITION 2 1 EAST \n"
					+ "FORWARD 1 \n"
					+ "WAIT \n"
					+ "TURNAROUND \n"
					+ "FORWARD 1 \n"
					+ "RIGHT \n"
					+ "FORWARD 2"))
			.andExpect(status().isOk());
	;
		}
}
