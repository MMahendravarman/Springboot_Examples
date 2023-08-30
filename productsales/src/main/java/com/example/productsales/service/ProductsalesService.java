package com.example.productsales.service;

import com.example.productsales.entity.Order;
import com.example.productsales.entity.Product;

public interface ProductsalesService {

	
	public Product addProduct(Product product);
	
	public Order createOrder(Order order);

	public int getSalesForProduct(int productId);
}
