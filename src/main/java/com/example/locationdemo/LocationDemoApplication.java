package com.example.locationdemo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
@EnableScheduling
@OpenAPIDefinition(
		info = @Info(
				title = "DEVICE MODULE API DOC",
				version = "1.0"))
public class LocationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationDemoApplication.class, args);
	}

}
