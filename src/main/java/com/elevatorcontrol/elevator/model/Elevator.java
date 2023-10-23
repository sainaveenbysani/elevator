package com.elevatorcontrol.elevator.model;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Document( collection = "elevators")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Elevator{
	
	@Id
	private String Id;
	
	private String elevatorIdentifier = UUID.randomUUID().toString();
		
	private String elevatorName;
	
	private List<Integer> floors;
	
	private Integer currentFloor;

	private State state;
	
	public enum State{ 
		UP, DOWN, STOPPED, OUT_OF_SERVICE
	}
}
	
