package com.elevatorcontrol.elevator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.elevatorcontrol.elevator.model.Elevator;
import com.elevatorcontrol.elevator.model.Elevator.State;
import com.elevatorcontrol.elevator.repository.ElevatorRepository;

@Service
public class ElevatorMovementService {
	
	@Autowired
	ElevatorRepository elevatorRepository;
	
	@Async
	public void moveElevator( Elevator elevator, Integer floor) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(elevator.getCurrentFloor()!= floor) {
			if(elevator.getCurrentFloor()<floor) {
				//elevator.setState(State.UP);
				elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
			}else if(elevator.getCurrentFloor()>floor) {
				//elevator.setState(State.DOWN);
				elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
			}
		}

		elevator.setState(State.STOPPED);	
		elevatorRepository.save(elevator);
	}
}
