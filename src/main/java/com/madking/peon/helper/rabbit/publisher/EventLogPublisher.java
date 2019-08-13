package com.madking.peon.helper.rabbit.publisher;


import com.madking.peon.dto.EventLogDTO;
import com.madking.peon.helper.rabbit.dispatch.TopicBuilder;
import com.madking.peon.pojo.RabbitPublisherSettingPOJO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventLogPublisher implements BasicPublisher{

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    TopicBuilder topicBuilder;

    private static String queueName = "ha.lazy.widget.event.q";
    private static String exchangeName = "etugoal.tx";
    private static String routingKeyPattern = "widget.event.#";
    private static RabbitPublisherSettingPOJO rabbitPublisherSetting = new RabbitPublisherSettingPOJO(queueName,exchangeName,routingKeyPattern);

    @Override
    public RabbitPublisherSettingPOJO getPublisherSetting(){
        return rabbitPublisherSetting;
    }

    @Override
    public void publish(Object objEventLogDTO){
        EventLogDTO eventLogDTO = (EventLogDTO) objEventLogDTO;
        String routingKey = "widget.event.all."+ eventLogDTO.getEvent();
        topicBuilder.doBuild(this.rabbitTemplate,rabbitPublisherSetting.getExchangeName(),rabbitPublisherSetting.getQueueName(),routingKey);
        this.rabbitTemplate.convertAndSend(rabbitPublisherSetting.getExchangeName(), routingKey,eventLogDTO);

    }
}
