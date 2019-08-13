package com.madking.peon.dto;

import java.io.Serializable;
import java.util.Date;

public class EventLogDTO implements Serializable {

    private String etuid;
    private String domain;
    private String groupId;
    private Integer widgetId;
    private String event;
    private Date ctime;

    public EventLogDTO(String etuid, String domain, String groupId, Integer widgetId, String event) {
        this.etuid = etuid;
        this.domain = domain;
        this.groupId = groupId;
        this.widgetId = widgetId;
        this.event = event;
        this.ctime = new Date();
    }

    public String getEtuid() {
        return etuid;
    }

    public String getDomain() {
        return domain;
    }

    public String getGroupId() {
        return groupId;
    }

    public Integer getWidgetId() {
        return widgetId;
    }

    public String getEvent() {
        return event;
    }

    public Date getCtime() {
        return ctime;
    }
}
