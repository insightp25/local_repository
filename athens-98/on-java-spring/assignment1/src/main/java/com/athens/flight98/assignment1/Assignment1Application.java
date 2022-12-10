package com.athens.flight98.assignment1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Assignment1Application {

	public static void main(String[] args) {
		SpringApplication.run(Assignment1Application.class, args);
	}

}