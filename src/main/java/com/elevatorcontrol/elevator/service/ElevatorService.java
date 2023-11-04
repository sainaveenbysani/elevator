package com.elevatorcontrol.elevator.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elevatorcontrol.elevator.dto.ElevatorDTO;
import com.elevatorcontrol.elevator.dto.ElevatorRequestDTO;
import com.elevatorcontrol.elevator.dto.ElevatorRequestDTO.UserState;
import com.elevatorcontrol.elevator.exception.ElevatorNotFoundException;
import com.elevatorcontrol.elevator.model.Elevator;
import com.elevatorcontrol.elevator.model.Elevator.State;
import com.elevatorcontrol.elevator.repository.BuildingRepository;
import com.elevatorcontrol.elevator.repository.ElevatorRepository;

@Service
public class ElevatorService{
	
	@Autowired
	ElevatorRepository elevatorRepository;
	
	@Autowired
	BuildingRepository buildingRepository;
	
	@Autowired
	ElevatorMovementService elevatorMovementService;
	
	public Elevator createElevator(ElevatorDTO elevator) {
		Elevator newElevator = new Elevator();
		newElevator.setElevatorName(elevator.getElevatorName());
		newElevator.setFloors(elevator.getFloors());
		newElevator.setCurrentFloor(elevator.getCurrentFloor());
		newElevator.setState(State.STOPPED);
		return elevatorRepository.save(newElevator);
	}

	public List<Elevator> getElevators(List<String> elevatorIdentifiers) {

		if(elevatorIdentifiers != null) {
			return elevatorRepository.findAllByElevatorIdentifierIn(elevatorIdentifiers);	
		}
		else {
			throw new ElevatorNotFoundException(" Elevators Not Found");
		}		
	}

	public Elevator summonElevator(List<Elevator> elevatorList, ElevatorRequestDTO elevatorRequest) {
		Integer userCurrentFloor = elevatorRequest.getUserCurrentFloor();
	    UserState userDesiredState = elevatorRequest.getUserDesiredState();  
	    List<Elevator> workingElevators = elevatorList.stream().filter(elevator-> elevator.getState()!= State.OUT_OF_SERVICE)
	    								  .toList();
	    Elevator nearestElevator = workingElevators.stream().filter(elevator-> elevator.getState()== State.STOPPED || elevator.getState().toString().equals(userDesiredState.toString()))
	    								  .min(Comparator.comparingInt(elevator -> Math.abs(userCurrentFloor - elevator.getCurrentFloor())))
	    								  .orElse(null);
//	    if( nearestElevator == null) {
//	    	// need to add the queuing mechanism to queue the requests.
//	    }
	    
	    // this below if else logic is not needed if some elevator is already moving towards the user
	    if(nearestElevator.getCurrentFloor()<userCurrentFloor) {
			nearestElevator.setState(State.UP);
		}else if(nearestElevator.getCurrentFloor()>userCurrentFloor) {
			nearestElevator.setState(State.DOWN);
		}
	    elevatorRepository.save(nearestElevator);
	    elevatorMovementService.moveElevator(nearestElevator, userCurrentFloor);
	    return nearestElevator;
	}
	

	public Elevator selectFloor(String elevatorIdentifier, Integer destinationFloor) {
		Optional<Elevator> selectedElevator = elevatorRepository.findByElevatorIdentifier(elevatorIdentifier);
		Elevator elevator = null;
		if(selectedElevator.isPresent()) {
			elevator = selectedElevator.get();
		}else {
			throw new ElevatorNotFoundException("Elevator with id " + elevatorIdentifier + " is not found" );
		}
		
		if(elevator.getCurrentFloor()== destinationFloor)
		{
			return elevator;
		}
		if(elevator.getCurrentFloor()<destinationFloor) {
			elevator.setState(State.UP);
		}else if(elevator.getCurrentFloor()>destinationFloor) {
			elevator.setState(State.DOWN);
		}
		elevatorRepository.save(elevator);
		elevatorMovementService.moveElevator(elevator, destinationFloor);
		return elevator;
	}

}