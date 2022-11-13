package com.athens.reweek04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ReWeek04Application {
    public static void main(String[] args) {
        SpringApplication.run(ReWeek04Application.class, args);
    }
}
