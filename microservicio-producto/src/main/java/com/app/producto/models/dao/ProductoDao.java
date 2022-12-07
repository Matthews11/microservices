package com.app.producto.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.app.producto.models.entity.Producto;

public interface ProductoDao extends CrudRepository<Producto, Long>{

}
