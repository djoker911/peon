package com.madking.peon.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfig {

    private String queueName;
    private String routingKey;
    private RabbitTemplate rabbitTemplate;
    private String exchangeName;

    public ProducerConfig() {

    }

    public ProducerConfig(String exchangeName,String queueName, String routingKey) {
        this.queueName = queueName;
        this.routingKey = routingKey;
        this.exchangeName=exchangeName;
        this.rabbitTemplate = rabbitTemplate();
        Queue currentQueue = buildQueue(queueName);
        Exchange currentExchange = buildTopicExchange(exchangeName);
        RabbitAdmin admin = new RabbitAdmin(this.rabbitTemplate.getConnectionFactory());
        admin.declareQueue(currentQueue);
        admin.declareExchange(currentExchange);
        admin.declareBinding(buildBinding(currentQueue, currentExchange,routingKey));
    }

    private Queue buildQueue(String queueName){
        return QueueBuilder.durable(queueName)
                .withArgument("x-message-ttl", 300000)
                .withArgument("x-queue-mode", "lazy")
                .build();
    }

    private Exchange buildTopicExchange(String exchangeName){
        return ExchangeBuilder.topicExchange(exchangeName)
                .durable(Boolean.TRUE)
                .build();
    }

    private Binding buildBinding(Queue queue, Exchange exchange, String routingKey){
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(routingKey)
                .noargs();
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }
    public String getQueueName() {
        return queueName;
    }

    public String getRoutingKey() {
        return routingKey;
    }
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setRoutingKey(this.routingKey);
        template.setQueue(this.queueName);
        return template;
    }

    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("Admin@123");
        connectionFactory.setConnectionTimeout(10000);
        connectionFactory.setVirtualHost("etugoal");
        return connectionFactory;
    }

    public void send(String s) {

        this.rabbitTemplate.convertAndSend(s);
    }

    public void send(String exchange,String routingKey,Object msg) {
        this.rabbitTemplate.convertAndSend(exchange,routingKey,msg);
    }
}
