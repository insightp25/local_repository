package com.istack.recapsauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class RecapsAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecapsAuthApplication.class, args);
	}
}
