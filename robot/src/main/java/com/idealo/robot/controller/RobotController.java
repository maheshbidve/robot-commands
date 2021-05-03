package com.idealo.robot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idealo.robot.constants.Constants;
import com.idealo.robot.model.Commands;
import com.idealo.robot.model.Direction;
import com.idealo.robot.model.Robot;

@RestController
@RequestMapping("/v1/robots")
public class RobotController {

	@PostMapping("/control")
	public Robot controlRobot(@RequestParam String script) {
		String[] commands = script.split("\\r?\\n");
		Robot robot = null;
		for (String command : commands) {
			if (command.contains("\"//\""));
				command = command.substring(0, command.indexOf("//"));
			
			String[] input = command.split(" ");
			Commands currentCommand = Commands.valueOf(input[0]);
			switch (currentCommand) {
			case POSITION:
				robot = new Robot(Integer.valueOf(input[1]), Integer.valueOf(input[2]), Direction.valueOf(input[3]));
				break;

			case FORWARD:
				robot = moveForward(robot, Integer.valueOf(input[1]));
				break;

			case TURNAROUND:
				robot.setDirection(robot.getDirection().getOpposite());
				break;

			case RIGHT:
				robot.setDirection(robot.getDirection().getRight());
				break;
				
			case LEFT:
				robot.setDirection(robot.getDirection().getLeft());
				break;

			case WAIT:
			default:
				break;

			}

		}
		return robot;
	}

	private Robot moveForward(Robot robot, Integer unit) {
		int result = 0;
		switch (robot.getDirection()) {
		case EAST:
			result = robot.getY() + unit;
			if (result > Constants.Y_MAX)
				throw new IllegalArgumentException("Invalid co-ordinated provided for the robot");
			robot.setY(result);
			break;

		case WEST:
			result = robot.getY() - unit;
			if (result < Constants.Y_MIN)
				throw new IllegalArgumentException("Invalid co-ordinated provided for the robot");

			robot.setY(result);
			break;

		case SOUTH:
			result = robot.getX() + unit;
			if (result > Constants.X_MAX)
				throw new IllegalArgumentException("Invalid co-ordinated provided for the robot");
			robot.setX(result);
			break;

		case NORTH:
			result = robot.getY() - unit;
			if (result < Constants.X_MIN)
				throw new IllegalArgumentException("Invalid co-ordinated provided for the robot");
			robot.setY(result);
			break;

		default:
			break;
		}
		return robot;

	}
}
