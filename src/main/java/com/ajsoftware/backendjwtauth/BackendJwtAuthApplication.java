package com.ajsoftware.backendjwtauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class BackendJwtAuthApplication {
	//TODO: se debe validar el proyecto sin esta funcionalidad
	public static void main(String[] args) {
		SpringApplication.run(BackendJwtAuthApplication.class, args);
	}

}
