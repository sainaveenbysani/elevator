package com.elevatorcontrol.elevator.exception;

public class BuildingNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 2L;

	public BuildingNotFoundException(String message) {	
		super(message);	
	}

}
