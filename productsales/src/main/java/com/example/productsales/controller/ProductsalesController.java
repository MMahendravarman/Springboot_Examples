package com.example.productsales.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.productsales.entity.Order;
import com.example.productsales.entity.Product;
import com.example.productsales.exception.ProductNotFoundException;
import com.example.productsales.repository.ProductsRepository;
import com.example.productsales.service.ProductsalesService;

@RestController
public class ProductsalesController {

	@Autowired
	ProductsalesService service;

	@Autowired
	ProductsRepository prodRepo;

	@PostMapping("/product")
	public Product addProduct(@RequestBody Product product) {
		return service.addProduct(product);
	}

	@PostMapping("/order/{productId}")
	public Order createOrder(@PathVariable int productId, @RequestBody Order order) throws Exception {
		Optional<Product> prod = prodRepo.findById(productId);
		if (prod.isPresent()) {
			Product product = prod.get();
			order.setProduct(product);
			return service.createOrder(order);
		} else {
			throw new ProductNotFoundException("Order failed.Product not found", HttpStatus.NOT_FOUND.value());
		}

	}

	@GetMapping("/productsales/{productId}")
	public int getSalesForProduct(@PathVariable int productId) {
		return service.getSalesForProduct(productId);
	}

	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}
}
