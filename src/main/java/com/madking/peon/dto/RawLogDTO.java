package com.madking.peon.dto;

import java.io.Serializable;
import java.util.Date;

public class RawLogDTO implements Serializable {
    private String etuid;
    private String domain;
    private String groupId;
    private String path;
    private Date ctime;

    public RawLogDTO(String etuid, String domain, String groupId, String path) {
        this.etuid = etuid;
        this.domain = domain;
        this.groupId = groupId;
        this.path = path;
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

    public String getPath() {
        return path;
    }

    public Date getCtime() {
        return ctime;
    }
}
