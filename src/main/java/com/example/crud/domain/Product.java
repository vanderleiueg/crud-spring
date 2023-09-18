package com.example.crud.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import com.example.crud.dtos.ProductRecordDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name="product")
@Table(name="product")
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
		
	private String name;
	
	private BigDecimal price;	
	
	@Column(name="status", columnDefinition="boolean default true")
	private boolean status;
	
    public Product(ProductRecordDto dto) {
		this.name = dto.name();
		this.price = dto.price();
	}

	public Product(UUID id, String name, BigDecimal price, boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public Product() {
		
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
    
    
}
