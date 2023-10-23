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
public class UserDTO{
	
	private String userIdentifier;
	
	private String userName;
	
	private List<String> buildingIdentifiers;
	
}