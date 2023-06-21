package com.consumerApp.entity.dto;

import com.consumerApp.entity.Event;
import com.consumerApp.entity.Session;
import lombok.Data;

import java.util.Date;
@Data
public class SessionOutputDto {
    private String sessionID;
    private String machineId;
    private Date createdDate;

    public SessionOutputDto(Session session){
        this.sessionID=session.getSessionId();
        this.machineId=session.getMachineId();
        this.createdDate=session.getCreatedDate();
    }
}
