package com.elevatorcontrol.elevator.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.elevatorcontrol.elevator.model.Elevator;

public interface ElevatorRepository extends MongoRepository<Elevator, String>{
	
	Optional<Elevator> findByElevatorIdentifier(String elevatorIdentifier);
	
	List<Elevator> findAllByElevatorIdentifierIn(List<String> elevatorIdentiers);
		
}