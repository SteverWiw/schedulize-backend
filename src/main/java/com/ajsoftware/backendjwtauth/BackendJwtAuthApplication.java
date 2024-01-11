package com.ajsoftware.backendjwtauth;

import com.ajsoftware.backendjwtauth.config.CorsConfig;
import com.ajsoftware.backendjwtauth.config.WebSocketConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//TODO: se debe validar el proyecto sin esta funcionalidad
@Import({CorsConfig.class, WebSocketConfig.class})
public class BackendJwtAuthApplication {
	//TODO: se debe validar el proyecto sin esta funcionalidad
	public static void main(String[] args) {
		SpringApplication.run(BackendJwtAuthApplication.class, args);
	}

}
