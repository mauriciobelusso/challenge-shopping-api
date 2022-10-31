package com.github.mauriciobelusso.challengeshoppingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ChallengeShoppingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeShoppingApiApplication.class, args);
	}

}
