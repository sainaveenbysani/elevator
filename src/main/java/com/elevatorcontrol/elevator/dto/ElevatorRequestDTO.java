package com.elevatorcontrol.elevator.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ElevatorRequestDTO {
  
	private Integer userCurrentFloor;
	
	private String buildingIdentifier;
	
	private UserState userDesiredState;
	
	public enum UserState{
		Up, Down;
	}	
}

