package com.example.productsales.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productsales.entity.Order;
import com.example.productsales.entity.Product;
import com.example.productsales.repository.OrdersRepository;
import com.example.productsales.repository.ProductsRepository;
import com.example.productsales.service.ProductsalesService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ProductsalesServiceImpl implements ProductsalesService{

	@Autowired
	ProductsRepository productRepo;
	
	@Autowired
	OrdersRepository orderRepo;
	
	public Product addProduct(Product product) {
		return productRepo.save(product);
	}

	@Override
	public Order createOrder(Order order) {
		return orderRepo.save(order);
	}

	@Override
	public int getSalesForProduct(int productId) {
		Optional<Product> prod = productRepo.findById(productId);
		int totalSales =0;
		List<Order> orders = orderRepo.findAllByProduct(prod.get());		
		for (Order order : orders) {
			
			totalSales = totalSales+order.getQty();
			
		}		
		return totalSales;		
	}
}
