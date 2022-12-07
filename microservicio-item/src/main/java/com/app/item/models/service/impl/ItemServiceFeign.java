package com.app.item.models.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.app.item.clientes.ProductoClienteRest;
import com.app.item.models.Item; 
import com.app.item.models.service.ItemService;

@Service("ItemServiceFeign")
@Primary
public class ItemServiceFeign implements ItemService {

	private final ProductoClienteRest rest;
	
	public ItemServiceFeign(ProductoClienteRest rest) {
		this.rest=rest;
	}
	@Override
	public List<Item> all() {
		return rest.all().stream().map(p-> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Optional<Item> findById(Long id, Integer cantidad) { 
		return  Optional.ofNullable(new Item(rest.findById(id).get(),cantidad));
	}

}
