package com.orden;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServicioOrdenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioOrdenApplication.class, args);
	}

}
