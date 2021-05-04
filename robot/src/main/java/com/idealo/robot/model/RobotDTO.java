package com.idealo.robot.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RobotDTO {
	private final Integer x;
	private final Integer y;
	private final Direction direction;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public RobotDTO(@JsonProperty("x") Integer x, @JsonProperty("y") Integer y, @JsonProperty("direction") Direction direction) {
		super();
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public Direction getDirection() {
		return direction;
	}

}
