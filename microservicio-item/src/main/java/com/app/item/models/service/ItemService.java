package com.app.item.models.service;

import java.util.List;
import java.util.Optional;

import com.app.item.models.Item;

public interface ItemService {

	List<Item> all();
	
	Optional<Item> findById(Long id, Integer cantidad);
}
