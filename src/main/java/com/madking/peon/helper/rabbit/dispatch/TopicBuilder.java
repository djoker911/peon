package com.madking.peon.helper.rabbit.dispatch;

import com.madking.peon.helper.rabbit.dispatch.BasicBuilder;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
public class TopicBuilder implements BasicBuilder {

    @Override
    public void doBuild(RabbitTemplate rabbitTemplate, String exchangeName, String queueName, String routingKey) {
        Queue currentQueue = buildQueue(queueName);
        Exchange currentExchange = buildExchange(exchangeName);
        RabbitAdmin admin = new RabbitAdmin(rabbitTemplate.getConnectionFactory());
        admin.declareQueue(currentQueue);
        admin.declareExchange(currentExchange);
        admin.declareBinding(buildBinding(currentQueue, currentExchange,routingKey));
    }

    @Override
    public Queue buildQueue(String queueName){
        return QueueBuilder.durable(queueName)
                .withArgument("x-message-ttl", 300000)
                .build();
    }

    @Override
    public Exchange buildExchange(String exchangeName){
        return ExchangeBuilder.topicExchange(exchangeName)
                .durable(Boolean.TRUE)
                .build();
    }

    @Override
    public Binding buildBinding(Queue queue, Exchange exchange, String routingKey){
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(routingKey)
                .noargs();
    }
}
