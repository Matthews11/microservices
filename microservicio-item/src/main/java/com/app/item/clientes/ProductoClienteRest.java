package com.app.item.clientes;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.item.models.Producto;

@FeignClient(name="servicio-productos", url = "localhost:8001")

public interface ProductoClienteRest {

	@GetMapping("/producto/all")
	public List<Producto> all();
	
	@GetMapping("/producto/find/{id}")
	public Optional<Producto> findById(@PathVariable Long id);
	
	
}
