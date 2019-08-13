package com.madking.peon.helper.rabbit.publisher;

import com.madking.peon.dto.RawLogDTO;
import com.madking.peon.helper.rabbit.dispatch.TopicBuilder;
import com.madking.peon.pojo.RabbitPublisherSettingPOJO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RawLogPublisher implements BasicPublisher {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    TopicBuilder topicBuilder;

    private static String queueName = "ha.lazy.widget.raw.q";
    private static String exchangeName = "etugoal.tx";
    private static String routingKeyPattern = "widget.raw.#";
    private static RabbitPublisherSettingPOJO rabbitPublisherSetting = new RabbitPublisherSettingPOJO(queueName,exchangeName,routingKeyPattern);

    @Override
    public RabbitPublisherSettingPOJO getPublisherSetting(){
        return rabbitPublisherSetting;
    }

    @Override
    public void publish(Object objRawLogDTO){
        RawLogDTO rawLogDTO = (RawLogDTO) objRawLogDTO;
        String routingKey = "widget.raw.all";
        topicBuilder.doBuild(this.rabbitTemplate,rabbitPublisherSetting.getExchangeName(),rabbitPublisherSetting.getQueueName(),routingKey);
        this.rabbitTemplate.convertAndSend(rabbitPublisherSetting.getExchangeName(), routingKey,rawLogDTO);

    }

}
