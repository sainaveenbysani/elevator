package com.elevatorcontrol.elevator.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElevatorDTO{
	
	private String elevatorIdentifier;
	
	private String elevatorName;
	
	private List<Integer> floors;
	
	private Integer currentFloor;
	
	private State state;	
	
	public enum State{ 
		UP, DOWN, STOPPED, OUT_OF_SERVICE
	}
}





	