package com.idealo.robot.model;

import org.springframework.lang.NonNull;

public class Robot {
	
	private @NonNull Integer x;
	private @NonNull Integer y;
	private @NonNull Direction direction;
	
	public Robot(Integer x, Integer y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public Robot() {};
	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}
