package com.example.crud.controllers;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.example.crud.domain.Product;
import com.example.crud.dtos.ProductRecordDto;
import com.example.crud.repositories.IProductRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private IProductRepository productRepository;
	
	@PostMapping	
	public ResponseEntity create(@RequestBody @Valid ProductRecordDto productDto){
		var product = new Product(productDto);
		
		var productSave = productRepository.save(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(productSave);
	}
	
	@GetMapping
	public ResponseEntity getAll() {
		var products = productRepository.findAll();
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity getById(@PathVariable UUID id){
		var productOptional = productRepository.findById(id);
		if(productOptional.isPresent()) {
			Product product = productOptional.get();
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(product);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable UUID id){
		var productOptional = productRepository.findById(id);
		if(productOptional.isPresent()) {
			Product product = productOptional.get();
			product.setStatus(!product.isStatus());
			productRepository.save(product);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(product);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
			
	
	@PutMapping
	public ResponseEntity update(@RequestBody @Valid ProductRecordDto productDto) {
		var productOptional = productRepository.findById(productDto.id());
		if(productOptional.isPresent()) {
			Product product = productOptional.get();
			product.setName(productDto.name());
			product.setPrice(productDto.price());
			productRepository.save(product);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(product);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
