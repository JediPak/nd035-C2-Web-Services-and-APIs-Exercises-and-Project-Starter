package com.eureka.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

//	http://localhost:8761/ -- eureka dashboard
	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);
	}

}
