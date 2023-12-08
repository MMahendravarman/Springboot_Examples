package com.example.springrabbitmqproducer.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest implements Serializable{
	
	private Integer orderId;
	private String productName;
	private int quantity;	

}
