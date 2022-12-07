package com.app.item.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Producto {
	
	private Long id;
	
	private String name;
	
	private Double price;
	
	private Date creatAt;

}
