package com.elevatorcontrol.elevator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elevatorcontrol.elevator.dto.BuildingDTO;
import com.elevatorcontrol.elevator.model.Building;
import com.elevatorcontrol.elevator.repository.BuildingRepository;

@Service
public class BuildingService{
	
	@Autowired
	BuildingRepository buildingRepository;
	
	public Building createBuilding(BuildingDTO building) {
		Building newBuilding = new Building();
		newBuilding.setBuildingName(building.getBuildingName());
		newBuilding.setLocation(building.getLocation());
		newBuilding.setElevatorIdentifiers(building.getElevatorIdentifiers());
		return buildingRepository.save(newBuilding);		
	}
	
	public List<Building> getBuildings(List<String> buildingIdentifiers){
		if(buildingIdentifiers!= null) {
			return buildingRepository.findAllByBuildingIdentifierIn(buildingIdentifiers);
		}
		else {
			return null;
			//throw new Exception("Buildings not found");
		}
	}
}