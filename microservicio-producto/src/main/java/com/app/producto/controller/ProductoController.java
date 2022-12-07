package com.app.producto.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.producto.models.entity.Producto;
import com.app.producto.models.service.IProductoService;
 

@RestController
@RequestMapping("/producto")
public class ProductoController {

	
	private final IProductoService service;
	
	public ProductoController (IProductoService service) {
		this.service=service;
	}
	
	@GetMapping("/all")
	public List<Producto> all(){
		return service.all();
	}
	
	@GetMapping("/find/{id}")
	public Producto findById(@PathVariable Long id){
		return service.findById(id).get();
	}
	
	
	
}
