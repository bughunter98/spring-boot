package com.udemypractise.SpringBootTutorialPractise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.udemypractise.SpringBootTutorialPractise"})
public class SpringBootTutorialPractiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTutorialPractiseApplication.class, args);
	}

}
