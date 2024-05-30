package com.istack.recapjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RecapjpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecapjpaApplication.class, args);
	}
}
