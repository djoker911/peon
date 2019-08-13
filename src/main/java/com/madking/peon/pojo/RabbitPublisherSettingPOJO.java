package com.madking.peon.pojo;

public class RabbitPublisherSettingPOJO {
    private String queueName;
    private String exchangeName;
    private String routingKeyPattern;

    public RabbitPublisherSettingPOJO(String queueName, String exchangeName,String routingKeyPattern) {
        this.queueName = queueName;
        this.exchangeName = exchangeName;
        this.routingKeyPattern = routingKeyPattern;
    }

    public String getQueueName() {
        return queueName;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public String getRoutingKeyPattern() {
        return routingKeyPattern;
    }
}
