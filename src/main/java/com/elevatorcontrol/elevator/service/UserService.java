package com.elevatorcontrol.elevator.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elevatorcontrol.elevator.dto.ElevatorDTO;
import com.elevatorcontrol.elevator.dto.UserDTO;
import com.elevatorcontrol.elevator.model.Building;
import com.elevatorcontrol.elevator.model.Elevator;
import com.elevatorcontrol.elevator.model.User;
import com.elevatorcontrol.elevator.repository.BuildingRepository;
import com.elevatorcontrol.elevator.repository.ElevatorRepository;
import com.elevatorcontrol.elevator.repository.UserRepository;

@Service
public class UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BuildingService buildingService;
	
	@Autowired
	BuildingRepository buildingRepository;

	@Autowired
	ElevatorService elevatorService;
	public User addUser(UserDTO newUser) {
		User user = new User();
		user.setUserName(newUser.getUserName());
		user.setBuildingIdentifiers(newUser.getBuildingIdentifiers());
		return userRepository.save(user);		
	}
	
	public User updateUser(String userId, UserDTO updatedUser) {
		Optional<User> userCheck = userRepository.findByUserIdentifier(userId);
		if(userCheck!= null) {
			User user = userCheck.get();
			if(updatedUser.getUserName()!= null) {		
			user.setUserName(updatedUser.getUserName());
			}
			if(updatedUser.getBuildingIdentifiers()!=null) {
			user.setBuildingIdentifiers(updatedUser.getBuildingIdentifiers());
			}
			return userRepository.save(user);
		}else {
			//Generate Custom Exception User Not found
			return null;
		}
	}
	
	public List<Building> getBuildings(String userId){
		Optional<User> user = userRepository.findByUserIdentifier(userId);
		if(user.isPresent()) {
			List<String> buildingIdentifiers = user.get().getBuildingIdentifiers();
			return buildingService.getBuildings(buildingIdentifiers);
		}
		else {
		    return null; //User Not found
		}		
	}

	public List<Elevator> getElevatorStatus(String userId, String buildingId) {
		Optional<User> user = userRepository.findByUserIdentifier(userId);
		if(user.isPresent()) {
			List<String> buildingIdentifiers = user.get().getBuildingIdentifiers();
			if(buildingIdentifiers.contains(buildingId)) {
				List<String> elevatorIdentifiers = buildingRepository.findByBuildingIdentifier(buildingId).get().getElevatorIdentifiers();
				return elevatorService.getElevators(elevatorIdentifiers);
			}
			else {
				return null; // building not found
			}
		}				
		else {
			return null; // user not found
		}
	}
}