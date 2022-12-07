package com.app.item.models.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.item.models.Item;
import com.app.item.models.Producto;
import com.app.item.models.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{

	private final RestTemplate clienteRest;
	
	public ItemServiceImpl(RestTemplate clienteRest) {
		this.clienteRest =clienteRest;
	}
	
	@Override
	public List<Item> all() {
		List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://localhost:8001/producto/all", Producto[].class));
		
		return productos.stream().map(p-> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Optional<Item> findById(Long id, Integer cantidad) {
		Map<String, String> variables= new HashMap<>();
		variables.put("id", id.toString());
		 Optional<Producto> oProducto = Optional.ofNullable(clienteRest.getForObject("http://localhost:8001/producto/find/{id}", Producto.class, variables));
		return Optional.ofNullable(new Item(oProducto.get(),cantidad));
	}

}
