package com.elevatorcontrol.elevator.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuildingDTO{
	
	private String buildingId;
	
	private String buildingName;
	
	private String location;
	
	private List<String> elevatorIdentifiers;	
	
}