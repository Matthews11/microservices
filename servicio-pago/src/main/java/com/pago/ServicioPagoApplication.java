package com.pago;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServicioPagoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioPagoApplication.class, args);
	}

}
