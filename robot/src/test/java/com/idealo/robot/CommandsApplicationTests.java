package com.idealo.robot;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.idealo.robot.controller.RobotController;

@SpringBootTest
class CommandsApplicationTests {

	@Autowired
	RobotController controller;
	
	@Test
	void contextLoads() {
		assertNotNull(controller);
	}

}
