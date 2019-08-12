package com.madking.peon.util;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "i am testTopic";
        System.out.println("Sender : " + context);
        String routingKey="topic.1";
        this.rabbitTemplate.convertAndSend("topicExchange", routingKey, context);
    }

    public void send1() {
        String context = "i am testTopic 1";
        System.out.println("Sender : " + context);
        String routingKey="topic.message";
        //检查routingKey "topic.message" 是否匹配QueueName.message（topic.message），QueueName.messages（topic.#）中的routingKey
        this.rabbitTemplate.convertAndSend("topicExchange", routingKey,context);
    }

    public void send2() {
        String context = "i am testTopic 2";
        System.out.println("Sender : " + context);
        String routingKey="topic.messages";
        this.rabbitTemplate.convertAndSend("topicExchange", routingKey,context);
    }

    public void send3() {
        String context = "i am testTopic 3 again and again";
        System.out.println("Sender : " + context);
        String routingKey="q1";
        this.rabbitTemplate.convertAndSend("testla", routingKey,context);
    }
}