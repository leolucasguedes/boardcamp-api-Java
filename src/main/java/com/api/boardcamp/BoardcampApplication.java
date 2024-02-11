package com.api.boardcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.api.boardcamp.models.entities")
public class BoardcampApplication {
	public static void main(String[] args) {
		SpringApplication.run(BoardcampApplication.class, args);
	}

}