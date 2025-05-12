package com.board.productbacklogitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.board.productbacklogitem.client")
public class ProductBacklogItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductBacklogItemApplication.class, args);
	}

}
