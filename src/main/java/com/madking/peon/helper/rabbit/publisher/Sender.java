package com.madking.peon.helper.rabbit.publisher;

import com.madking.peon.helper.rabbit.dispatch.TopicBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
//    @Autowired
//    private AmqpTemplate rabbitTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicBuilder topicBuilder;

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
        this.rabbitTemplate.convertAndSend("topicExchange", routingKey,context);
    }

    public void send2() {
        String context = "i am testTopic 2 , i will create non exist shit";
        System.out.println("Sender : " + context);
        String routingKey="topic.messages";
        String exchange = "topicExchange";
        topicBuilder.doBuild(this.rabbitTemplate,exchange,"testFromNothing",routingKey);
        this.rabbitTemplate.convertAndSend("topicExchange", routingKey,context);

    }

    public void send3() {
        String context = "i am testTopic 3 again and again and fuck u la";
        System.out.println("Sender : " + context);
        String routingKey="q1";
        this.rabbitTemplate.convertAndSend("testla", routingKey,context);
    }
}