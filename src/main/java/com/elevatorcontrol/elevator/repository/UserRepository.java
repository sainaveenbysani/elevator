package com.elevatorcontrol.elevator.repository;

import java.util.Optional;

//import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.elevatorcontrol.elevator.model.User;


public interface UserRepository extends MongoRepository<User, String>{
	
	boolean existsByUserIdentifier(String userIdentifier);
	
	Optional<User> findByUserIdentifier(String userIdentifier);
	
}