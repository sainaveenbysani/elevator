package com.elevatorcontrol.elevator.exception;

public class ElevatorNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 3L;

	public ElevatorNotFoundException(String message) {
		super(message);
	}
}
