package com.app.gateway.filters;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
//import org.springframework.http.MediaType; 
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class ExampleGlobalFilter implements GlobalFilter, Ordered{

	private final Logger logger = LoggerFactory.getLogger(ExampleGlobalFilter.class);
	
	//se implementa un filtro donde antes o despues de realizar una accion http realizara una tarea 
	// como patron proxy
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("ejecutando pre filtro");
		exchange.getRequest().mutate().headers(h-> h.add("token", "12345"));
		
		return chain.filter(exchange).then(Mono.fromRunnable(()-> {
			logger.info("ejecutanto filtro post");
			
			Optional.ofNullable( exchange.getRequest().getHeaders().getFirst("token")).ifPresent(valor->{
				exchange.getResponse().getHeaders().add("token", valor);
			});
			
//			exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
		}));
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

}
