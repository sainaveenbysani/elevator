package com.elevatorcontrol.elevator.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.elevatorcontrol.elevator.dto.ElevatorDTO;
import com.elevatorcontrol.elevator.model.Elevator;
import com.elevatorcontrol.elevator.service.ElevatorService;
import com.elevatorcontrol.elevator.responsewrapper.*;


@RestController
@RequestMapping(path = "/api/elevators")
public class ElevatorController{
	
	@Autowired
	ElevatorService elevatorService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Elevator createElevator(@RequestBody ElevatorDTO elevator) {		
		return elevatorService.createElevator(elevator);		
	}
	
	@PutMapping(path = "/{id}/elevator/select-floor")
	public ElevatorResponse selectFloor(@PathVariable(value = "id") String elevatorIdentifier, @RequestParam Integer destinationFloor) {
		ElevatorResponse elevatorResponse = ElevatorResponse.builder().message("Floor Selected and Elevator Start Moving!!!")
		   .elevator(elevatorService.selectFloor(elevatorIdentifier, destinationFloor)).build();
		return elevatorResponse;
	}
	
}