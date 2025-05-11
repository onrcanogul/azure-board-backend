package com.board.bug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BugApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BugApiApplication.class, args);
	}

}
