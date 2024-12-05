package com.demand_service.demand_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing
@EntityScan(basePackages = "com.demand_service.demand_service.demand")

public class DemandServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemandServiceApplication.class, args);
	}

}
