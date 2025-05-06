package com.board.team;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TeamApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamApiApplication.class, args);
	}

}
