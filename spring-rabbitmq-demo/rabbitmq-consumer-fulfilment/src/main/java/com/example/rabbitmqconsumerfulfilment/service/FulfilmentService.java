package com.example.rabbitmqconsumerfulfilment.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.rabbitmqconsumerfulfilment.model.OrderRequest;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FulfilmentService {

	@RabbitListener(queues = "q.order-fulfilment")
    public void fulFilOrder(OrderRequest message) {    	
        log.info("Order Fulfilment :"+ message.getProductName());
        //Add Logic for order fulfilment
    }
}

