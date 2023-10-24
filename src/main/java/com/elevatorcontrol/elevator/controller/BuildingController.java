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

import com.elevatorcontrol.elevator.dto.BuildingDTO;
import com.elevatorcontrol.elevator.model.Building;
import com.elevatorcontrol.elevator.service.BuildingService;


@RestController
@RequestMapping(path = "/api/buildings")
public class BuildingController{
	
	@Autowired
	BuildingService buildingService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Building createBuilding(@RequestBody BuildingDTO building) {		
		return buildingService.createBuilding(building);		
	}
	
	
//	@GetMapping(path = "/{id}")
//	public BuildingDTO getBuilding(@PathVariable(value = "id") int buildingId) {
//		return null;
//		
//	}
//	
//	@PutMapping(path = "/{id}")
//	public BuildingDTO updateBuilding(@PathVariable(value= "id") int buildingId, @RequestBody BuildingDTO building) {
//		return null;
//		
//	}
//	
//	@DeleteMapping(path = "/{id}")
//	public BuildingDTO deleteBuilding(@PathVariable(value= "id") int buildingId) {
//		return null;
//		
//	}
}