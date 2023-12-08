package com.example.springrabbitmqproducer.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

	@Bean
	public Declarables createOrderNotificationSchema() {

		FanoutExchange fanoutExchange = new FanoutExchange("x.order-notification");

		Queue fulfilment = new Queue("q.order-fulfilment");
		Queue payment = new Queue("q.order-payment");

		return new Declarables(fanoutExchange, 
				fulfilment,
				payment,
				BindingBuilder.bind(payment).to(fanoutExchange),
				BindingBuilder.bind(fulfilment).to(fanoutExchange)
				);

	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter());
		return rabbitTemplate;
	}

	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
