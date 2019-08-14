package com.madking.peon.service.impl;

import com.madking.peon.dto.EventLogDTO;
import com.madking.peon.dto.RawLogDTO;
import com.madking.peon.helper.rabbit.publisher.EventLogPublisher;
import com.madking.peon.helper.rabbit.publisher.RawLogPublisher;
import com.madking.peon.service.ReceivingBehaviorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReceivingBehaviorServiceImpl implements ReceivingBehaviorService {

    private RawLogPublisher rawLogPublisher;
    private EventLogPublisher eventLogPublisher;

    @Autowired
    public ReceivingBehaviorServiceImpl(RawLogPublisher rawLogPublisher,
                                        EventLogPublisher eventLogPublisher){
        this.rawLogPublisher = rawLogPublisher;
        this.eventLogPublisher = eventLogPublisher;
    }

    @Override
    public Boolean publishRawLog(String etuid, String domain, String groupId, String path){
        RawLogDTO rawLogDTO = new RawLogDTO(etuid, domain, groupId, path);
        Boolean result = Boolean.FALSE;
        try {
            rawLogPublisher.publish(rawLogDTO);
            result = Boolean.TRUE;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return result;
        }
    }
    @Override
    public Boolean publishClickEvent(String etuid, String domain, String groupId, Integer widgetId){
        return publishEvent(etuid, domain, groupId, widgetId, "click");
    }
    @Override
    public Boolean publishDisplayEvent(String etuid, String domain, String groupId, Integer widgetId){
        return publishEvent(etuid, domain, groupId, widgetId,"display");
    }

    private Boolean publishEvent(String etuid, String domain, String groupId, Integer widgetId, String event){
        EventLogDTO eventLogDTO = new EventLogDTO(etuid, domain, groupId, widgetId, event);
        Boolean result = Boolean.FALSE;
        try{
            eventLogPublisher.publish(eventLogDTO);
            result = Boolean.TRUE;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return result;
        }
    }
}
