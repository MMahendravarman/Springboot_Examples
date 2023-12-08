package com.example.rabbitmqconsumerpayment.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.rabbitmqconsumerpayment.model.OrderRequest;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class PaymentService {

	@RabbitListener(queues = "q.order-fulfilment")
    public void payment(OrderRequest message) {    	
        log.info("Order Payment :"+ message.getProductName());
        //Add Logic for order payment
    }

}
