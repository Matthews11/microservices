package com.app.item.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Item {

	private Producto producto;
	
	private Integer cantidad;
	
	public Double getTotal() {
		return producto.getPrice() * this.cantidad.doubleValue();
	}
	
}
