package com.elevatorcontrol.elevator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElevatorApplication {

	public static void main(String[] args) { 
		SpringApplication.run(ElevatorApplication.class, args);
		System.out.println("My Control is in your hand");
	}
}
