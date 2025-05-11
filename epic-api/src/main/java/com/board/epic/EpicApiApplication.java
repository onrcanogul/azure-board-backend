package com.board.epic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EpicApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpicApiApplication.class, args);
	}

}
