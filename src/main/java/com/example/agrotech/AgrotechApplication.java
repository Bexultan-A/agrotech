package com.example.agrotech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AgrotechApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgrotechApplication.class, args);
	}

}
