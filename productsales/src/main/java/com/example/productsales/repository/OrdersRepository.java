package com.example.productsales.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.productsales.entity.Order;
import com.example.productsales.entity.Product;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer>{

	List<Order> findAllByProduct(Product product);

}
