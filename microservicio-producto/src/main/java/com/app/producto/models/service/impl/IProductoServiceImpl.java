package com.app.producto.models.service.impl;

import java.util.List;
import java.util.Optional;
 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.producto.models.dao.ProductoDao;
import com.app.producto.models.entity.Producto;
import com.app.producto.models.service.IProductoService;

@Service
public class IProductoServiceImpl implements IProductoService{

	private final ProductoDao dao;
	
	public IProductoServiceImpl (ProductoDao dao) {
		this.dao= dao;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> all() {
		return (List<Producto>) dao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Producto> findById(Long id) {
		return Optional.ofNullable(dao.findById(id).orElse(null));
	}

}
