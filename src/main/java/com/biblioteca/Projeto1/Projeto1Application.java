package com.biblioteca.Projeto1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Projeto1Application {

	public static void main(String[] args) {
		SpringApplication.run(Projeto1Application.class, args);
	}

	@GetMapping("/")
	private String index() {
		return "Greetings from Spring Boot";
	}
	
}
