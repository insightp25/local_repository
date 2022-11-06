package com.athens.week03;

import com.athens.week03.domain.Memo;
import com.athens.week03.repository.MemoRepository;
import com.athens.week03.service.MemoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class Week03Application {

	public static void main(String[] args) {

		SpringApplication.run(Week03Application.class, args);
	}


}
