package com.elevatorcontrol.elevator.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.elevatorcontrol.elevator.model.Building;

public interface BuildingRepository extends MongoRepository<Building, String>{
	
	Optional<Building> findByBuildingIdentifier(String buildingIdentifier);
	
	List<Building> findAllByBuildingIdentifierIn(List<String> BuildingIdentifiers);
		
}