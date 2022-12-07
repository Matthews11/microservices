package com.app.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.item.models.Item;
import com.app.item.models.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Qualifier("ItemServiceFeign")
	private final ItemService service;
	
	public ItemController (@Qualifier("ItemServiceFeign")ItemService  service) {
		this.service=service;
	}
	
	@GetMapping("/all")
	public List<Item> all(){
		return service.all();
	}
	
	@GetMapping("/find/{id}/{cantidad}")
	public Item findIdAndCantidad(@PathVariable Long id, @PathVariable Integer cantidad){
		return service.findById(id, cantidad).get();
	}
	
	
}
