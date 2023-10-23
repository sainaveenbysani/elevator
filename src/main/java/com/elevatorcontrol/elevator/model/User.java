package com.elevatorcontrol.elevator.model;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document( collection = "users")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{

	@Id
	private String id;
	
	private String userIdentifier = UUID.randomUUID().toString();
	
	private String userName;
		
	private List<String> buildingIdentifiers;
	
}