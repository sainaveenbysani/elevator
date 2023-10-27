package com.elevatorcontrol.elevator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.elevatorcontrol.elevator.dto.ElevatorRequestDTO;
import com.elevatorcontrol.elevator.dto.UserDTO;
import com.elevatorcontrol.elevator.model.Building;
import com.elevatorcontrol.elevator.model.Elevator;
import com.elevatorcontrol.elevator.model.User;
import com.elevatorcontrol.elevator.responsewrapper.ElevatorResponse;
import com.elevatorcontrol.elevator.service.UserService;
//import com.elevatorcontrol.elevator.dto.

@RestController
@RequestMapping(path = "/api/users")
public class UserController{
	
	@Autowired
	UserService userService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User addUser(@RequestBody UserDTO newUser) {		
		return userService.addUser(newUser);
	}
	
	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public User updateUser(@PathVariable(value= "id") String userId, @RequestBody UserDTO updatedUser) {
		return userService.updateUser(userId, updatedUser);		
	}

	@GetMapping(path = "/{id}/buildings")
	public List<Building> getBuildings(@PathVariable(value = "id") String userId) {
		return userService.getBuildings(userId);		
	}
	
	@GetMapping(path = "/{id}/building/elevators")
	public List<Elevator> getElevatorStatus(@PathVariable(value = "id") String userId, 
			@RequestParam String buildingId) {
		return userService.getElevatorStatus(userId, buildingId);		
	}
	
	@PostMapping(path = "/{id}/elevator/summon")
	public ElevatorResponse summonElevator(@PathVariable(value = "id") String userId, @RequestBody ElevatorRequestDTO elevatorRequest) {
		ElevatorResponse elevatorResponse = ElevatorResponse.builder().message("Elevator Selected and Elevator Start Moving!!!")
		   .elevator(userService.summonElevator(userId, elevatorRequest)).build();
		return elevatorResponse;		
	}
	
//	
//	@GetMapping(path = "/{id}")
//	public User getUser(@PathVariable(value = "id") int userId) {
//		return null;		
//	}
	
//	@DeleteMapping(path = "/{id}")
//	public User deleteUser(@PathVariable(value= "id") int userId) {
//		return null;		
//	}
}