package com.idealo.robot.model;

public enum Direction {
	EAST,

	WEST,

	NORTH,

	SOUTH;

	public Direction getOpposite() {
		switch (this) {
		case NORTH:
			return SOUTH;
		case SOUTH:
			return NORTH;
		case EAST:
			return WEST;
		case WEST:
			return EAST;
		}
		throw new RuntimeException("Case not implemented");
	}
	
	public Direction getRight() {
		switch (this) {
		case NORTH:
			return EAST;
		case SOUTH:
			return WEST;
		case EAST:
			return SOUTH;
		case WEST:
			return NORTH;
		}
		throw new RuntimeException("Case not implemented");
	}
	
	public Direction getLeft() {
		switch (this) {
		case NORTH:
			return WEST;
		case SOUTH:
			return EAST;
		case EAST:
			return NORTH;
		case WEST:
			return SOUTH;
		}
		throw new RuntimeException("Case not implemented");
	}

}
