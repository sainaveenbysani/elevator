package com.elevatorcontrol.elevator.model;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document( collection = "buildings")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Building{
	
	@Id
	private String id;
	
	private String buildingIdentifier= UUID.randomUUID().toString();

	private String buildingName;
	
	private String location;	
	
	private List<String> elevatorIdentifiers;
}