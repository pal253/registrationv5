package com.api5;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Registration5Application {

	public static void main(String[] args) {
		SpringApplication.run(Registration5Application.class, args);
	}
@Bean
	public ModelMapper getmp(){
		return new ModelMapper();
}
}
