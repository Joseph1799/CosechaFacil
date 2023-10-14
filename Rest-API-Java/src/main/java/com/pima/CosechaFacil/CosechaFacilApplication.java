package com.pima.CosechaFacil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CorsConfig.class)
public class CosechaFacilApplication {

	public static void main(String[] args) {
		SpringApplication.run(CosechaFacilApplication.class, args);
	}

}
