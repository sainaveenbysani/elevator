package com.elevatorcontrol.elevator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elevatorcontrol.elevator.dto.ElevatorDTO;
import com.elevatorcontrol.elevator.model.Elevator;
import com.elevatorcontrol.elevator.model.Elevator.State;
import com.elevatorcontrol.elevator.repository.ElevatorRepository;

@Service
public class ElevatorService{
	
	@Autowired
	ElevatorRepository elevatorRepository;
	
	public Elevator createElevator(ElevatorDTO elevator) {
		Elevator newElevator = new Elevator();
		newElevator.setElevatorName(elevator.getElevatorName());
		newElevator.setFloors(elevator.getFloors());
		newElevator.setCurrentFloor(elevator.getCurrentFloor());
		newElevator.setState(State.STOPPED);
		return elevatorRepository.save(newElevator);
	}
}