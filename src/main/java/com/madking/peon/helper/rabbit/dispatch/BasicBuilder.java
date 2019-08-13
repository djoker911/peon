package com.madking.peon.helper.rabbit.dispatch;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public interface BasicBuilder {

    void doBuild(RabbitTemplate rabbitTemplate, String exchangeName, String queueName, String routingKey);
    Queue buildQueue(String queueName);
    Exchange buildExchange(String exchangeName);
    Binding buildBinding(Queue queue, Exchange exchange, String routingKey);
}
