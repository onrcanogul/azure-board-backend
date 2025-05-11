package com.board.feature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeatureApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeatureApiApplication.class, args);
	}

}
