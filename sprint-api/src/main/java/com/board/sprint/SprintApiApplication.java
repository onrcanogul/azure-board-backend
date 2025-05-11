package com.board.sprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.board.sprint.client")
public class SprintApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintApiApplication.class, args);
	}

}
