package com.elevatorcontrol.elevator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> UserNotFoundExceptionHandler(UserNotFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND) ;	
	}
	
	@ExceptionHandler(BuildingNotFoundException.class)
	public ResponseEntity<ErrorResponse> BuildingNotFoundExceptionHandler(BuildingNotFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND) ;	
	}
	
	@ExceptionHandler(ElevatorNotFoundException.class)
	public ResponseEntity<ErrorResponse> ElevatorNotFoundExceptionHandler(ElevatorNotFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND) ;	
	}
}
