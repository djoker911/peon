package com.madking.peon.helper.rabbit.publisher;

import com.madking.peon.pojo.RabbitPublisherSettingPOJO;

public interface BasicPublisher {
    void publish(Object message);
    RabbitPublisherSettingPOJO getPublisherSetting();
}
