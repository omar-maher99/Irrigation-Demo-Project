package com.irrigationSystem.irrigationSystemApp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class IrrigationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(IrrigationSystemApplication.class, args);
	}

	//Bean to be able to use the model mapper
	@Configuration
	public class AppConfiguration {
		@Bean
		public ModelMapper modelMapper() {
			return new ModelMapper();
		}
	}
}
