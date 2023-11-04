package com.elevatorcontrol.elevator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ElevatorConfig {
	
	@Bean
    public TaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor() {
			private static final long serialVersionUID = 5L;

			@Override
	        public void destroy() {
	            super.shutdown();
	        }		
		};
		taskExecutor.setCorePoolSize(3);
		taskExecutor.setMaxPoolSize(10);
		taskExecutor.setWaitForTasksToCompleteOnShutdown(true); 
		taskExecutor.setAwaitTerminationSeconds(30); 
		taskExecutor.initialize();	
		return taskExecutor;		
	}
}
