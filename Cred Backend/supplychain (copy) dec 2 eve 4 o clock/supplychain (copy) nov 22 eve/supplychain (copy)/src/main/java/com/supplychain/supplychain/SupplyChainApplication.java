package com.supplychain.supplychain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SupplyChainApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupplyChainApplication.class, args);
	}

}
