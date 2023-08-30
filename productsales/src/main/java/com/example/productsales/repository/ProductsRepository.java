package com.example.productsales.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.productsales.entity.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer>{

}
