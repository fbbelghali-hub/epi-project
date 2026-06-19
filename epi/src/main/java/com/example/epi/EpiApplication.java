package com.example.epi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@SpringBootApplication
@EnableJpaAuditing
public class EpiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpiApplication.class, args);
	}

}
