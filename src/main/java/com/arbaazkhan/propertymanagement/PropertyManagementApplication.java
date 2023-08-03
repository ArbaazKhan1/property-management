package com.arbaazkhan.propertymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2 //This is from springfox dependency and will allow for documentation of our api endpoints
public class PropertyManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(PropertyManagementApplication.class, args);
	}

}
