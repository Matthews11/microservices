package com.app.producto.models.service;

import java.util.List;
import java.util.Optional;

import com.app.producto.models.entity.Producto;

public interface IProductoService {

	List<Producto> all();
	
	Optional<Producto> findById(Long id);
}
