package com.example.productsales.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.productsales.entity.Order;
import com.example.productsales.entity.Product;
import com.example.productsales.repository.OrdersRepository;
import com.example.productsales.repository.ProductsRepository;
import com.example.productsales.service.ProductsalesService;
import com.example.productsales.service.impl.ProductsalesServiceImpl;


@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "test")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
public class ProductsalesControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean	
	OrdersRepository orderRepo;

	@MockBean	
	ProductsRepository productRepo;

	@Autowired
	ProductsalesService svc;
	
	
	@Test
	public void testGetSalesForProduct() throws URISyntaxException {

		final String baseUrl = "/productsales/2";

		Optional<Product> prod = Optional.ofNullable(Product.builder().productId(1).productName("mobile").build());

		Order order1 = Order.builder().orderId(1).product(prod.get()).qty(5).build();
		Order order2= Order.builder().orderId(2).product(prod.get()).qty(5).build();

		List<Order> orders = new ArrayList<>();

		orders.add(order1);
		orders.add(order2);

		when(productRepo.findById(any())).thenReturn(prod);

		when(orderRepo.findAllByProduct(any())).thenReturn(orders);

		ResponseEntity<Integer> result = this.restTemplate.getForEntity(baseUrl, Integer.class);

		Assertions.assertEquals(200, result.getStatusCode().value());

		Assertions.assertEquals(10, result.getBody().intValue());
	}

}
