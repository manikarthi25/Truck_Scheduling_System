package com.erukka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TruckSchedulingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TruckSchedulingApplication.class, args);
	}

}
