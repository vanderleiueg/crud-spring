package com.example.crud.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.dtos.ProductRecordDto;
import com.example.crud.models.Product;
import com.example.crud.repositories.IProductRepository;

import jakarta.validation.Valid;

@RestController
public class ProductController {

	@Autowired
	IProductRepository productRepository;
	
	@PostMapping("/products")
	public ResponseEntity<Product> create(@RequestBody @Valid ProductRecordDto productDto){
		var product = new Product();
		BeanUtils.copyProperties(productDto, product);
		return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
	}
}
