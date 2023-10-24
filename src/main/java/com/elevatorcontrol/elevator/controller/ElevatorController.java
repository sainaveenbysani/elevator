package com.elevatorcontrol.elevator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.elevatorcontrol.elevator.dto.ElevatorDTO;
import com.elevatorcontrol.elevator.model.Elevator;
import com.elevatorcontrol.elevator.service.ElevatorService;


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
	
	
	@GetMapping
	public List<Integer> getAllElevators(){
		return null;
	}
	
	@GetMapping(path = "/{id}")
	public ElevatorDTO getElevator(@PathVariable(value = "id") int elevatorId) {
		return null;
		
	}
	
	@PutMapping(path = "/{id}")
	public ElevatorDTO updateElevator(@PathVariable(value= "id") int elevatorId, @RequestBody ElevatorDTO elevator) {
		return null;
		
	}
	
	@DeleteMapping(path = "/{id}")
	public ElevatorDTO deleteElevator(@PathVariable(value= "id") int elevatorId) {
		return null;
		
	}
}