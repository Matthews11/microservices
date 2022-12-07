package com.app.producto.models.entity;
 

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="productos")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Producto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4980693647530752269L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Double price;
	
	@Column(name="creat_at",nullable = false)
	@Temporal(TemporalType.DATE)
	private Date creatAt;
}
