package com.idealo.robot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.idealo.robot.model.Direction;
import com.idealo.robot.model.RobotDTO;

@SpringBootTest(classes = { CommandsApplication.class }, webEnvironment = WebEnvironment.RANDOM_PORT)

public class IntegrationTests {
	@Autowired
	@LocalServerPort
	private int serverPort;
	
	@Value("${host}")
	private String host;

	private TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void test_robot_movement() {
		String consumeUrl = "http://" + host + ":" + serverPort
				+ "v1/robots/control?script=" + "POSITION 1 3 EAST \n"
						+ "FORWARD 1 \n"
						+ "WAIT \n"
						+ "TURNAROUND \n"
						+ "FORWARD 1 \n"
						+ "RIGHT \n"
						+ "FORWARD 2";

		ResponseEntity<RobotDTO> responseEntity = restTemplate.exchange(consumeUrl, HttpMethod.POST, null,
				new ParameterizedTypeReference<RobotDTO>() {
				});

		RobotDTO response = responseEntity.getBody();

		assertFalse(Objects.isNull(response));
		assertEquals(response.getX(), 3);
		assertEquals(response.getY(), 3);
		assertEquals(response.getDirection(), Direction.NORTH);

	}
	
	@Test
	public void test_robot_movement_with_west_direction() {
		String consumeUrl = "http://" + host + ":" + serverPort
				+ "v1/robots/control?script=" + "POSITION 0 0 NORTH \n"
						+ "FORWARD 3 \n"
						+ "WAIT \n"
						+ "RIGHT \n"
						+ "FORWARD 2";

		ResponseEntity<RobotDTO> responseEntity = restTemplate.exchange(consumeUrl, HttpMethod.POST, null,
				new ParameterizedTypeReference<RobotDTO>() {
				});

		RobotDTO response = responseEntity.getBody();

		assertFalse(Objects.isNull(response));
		assertEquals(response.getX(), 3);
		assertEquals(response.getY(), 2);
		assertEquals(response.getDirection(), Direction.EAST);

	}
	
	@Test
	public void test_robot_movement_with_south_direction() {
		String consumeUrl = "http://" + host + ":" + serverPort
				+ "v1/robots/control?script=" + "POSITION 5 5 SOUTH \n"
						+ "RIGHT \n"
						+ "WAIT \n"
						+ "FORWARD 2";

		ResponseEntity<RobotDTO> responseEntity = restTemplate.exchange(consumeUrl, HttpMethod.POST, null,
				new ParameterizedTypeReference<RobotDTO>() {
				});

		RobotDTO response = responseEntity.getBody();

		assertFalse(Objects.isNull(response));
		assertEquals(response.getX(), 5);
		assertEquals(response.getY(), 3);
		assertEquals(response.getDirection(), Direction.WEST);

	}
}
