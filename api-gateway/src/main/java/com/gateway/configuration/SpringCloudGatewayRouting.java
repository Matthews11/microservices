package com.gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudGatewayRouting {

	@Bean
	public RouteLocator configureRoute(RouteLocatorBuilder builder) {
		 return builder.routes()
			      .route("paymentId", r->r.path("/payment/**").uri("http://localhost:9009")) //estatico  mapeado a la ruta del microservicio
			      .route("orderId", r->r.path("/order/**").uri("lb://ORDER-SERVICE")) //dinamico porque se mapeo al nombre del microservicio aunque se cambie el puerto funcionara 
			      .build();
	} 
}
