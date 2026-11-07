package com.markelly.events_microservise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EventsMicroserviseApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventsMicroserviseApplication.class, args);
	}

}
