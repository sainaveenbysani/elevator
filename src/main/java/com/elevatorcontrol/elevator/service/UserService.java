package com.elevatorcontrol.elevator.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elevatorcontrol.elevator.dto.ElevatorRequestDTO;
import com.elevatorcontrol.elevator.dto.UserDTO;
import com.elevatorcontrol.elevator.exception.BuildingNotFoundException;
import com.elevatorcontrol.elevator.exception.UserNotFoundException;
import com.elevatorcontrol.elevator.model.Building;
import com.elevatorcontrol.elevator.model.Elevator;
import com.elevatorcontrol.elevator.model.User;
import com.elevatorcontrol.elevator.repository.BuildingRepository;
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
		if(userCheck.isPresent()) {
			User user = userCheck.get();
			if(updatedUser.getUserName()!= null) {		
			user.setUserName(updatedUser.getUserName());
			}
			if(updatedUser.getBuildingIdentifiers()!=null) {
			user.setBuildingIdentifiers(updatedUser.getBuildingIdentifiers());
			}
			return userRepository.save(user);
		}else {
			throw new UserNotFoundException("User with id "+userId+" not found.");
		}
	}
	
	public List<Building> getBuildings(String userId){
		Optional<User> user = userRepository.findByUserIdentifier(userId);
		if(user.isPresent()) {
			List<String> buildingIdentifiers = user.get().getBuildingIdentifiers();
			return buildingService.getBuildings(buildingIdentifiers);
		}
		else {
			throw new UserNotFoundException("User with id "+userId+" not found.");
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
				throw new BuildingNotFoundException("Building with id "+buildingId+" not found.");
			}
		}				
		else {
			throw new UserNotFoundException("User with id "+userId+" not found.");
		}
	}

	public Elevator summonElevator(String userId, ElevatorRequestDTO elevatorRequest) {
		
		if(userRepository.findByUserIdentifier(userId).isEmpty()) {
			throw new UserNotFoundException("User with id "+ userId + " not found.");
		}	
		return elevatorService.summonElevator(getElevatorStatus(userId, elevatorRequest.getBuildingIdentifier()), elevatorRequest);
	 }
			
}