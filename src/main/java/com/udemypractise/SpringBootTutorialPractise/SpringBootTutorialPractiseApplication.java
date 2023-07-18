package com.udemypractise.SpringBootTutorialPractise;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.udemypractise.SpringBootTutorialPractise"})
@OpenAPIDefinition(info = @Info(
		title = "Spring Boot REST API Documentation",
		description = "Spring Boot REST API Documentation",
		version = "v1.0",
		contact = @Contact(name = "Sai Teja" , email = "venkatasaitejathota97@gmail.com")
))
public class SpringBootTutorialPractiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTutorialPractiseApplication.class, args);
	}

}
