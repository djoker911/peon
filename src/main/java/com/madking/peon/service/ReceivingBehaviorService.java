package com.madking.peon.service;

public interface ReceivingBehaviorService {
    Boolean publishRawLog(String etuid, String domain, String groupId, String path);
    Boolean publishClickEvent(String etuid, String domain, String groupId, Integer widgetId);
    Boolean publishDisplayEvent(String etuid, String domain, String groupId, Integer widgetId);
}
