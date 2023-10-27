package com.elevatorcontrol.elevator.responsewrapper;

import com.elevatorcontrol.elevator.model.Elevator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElevatorResponse {
	
	private String message;
	
	private Elevator elevator;

}
