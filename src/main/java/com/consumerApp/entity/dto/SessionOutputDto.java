package com.consumerApp.entity.dto;

import com.consumerApp.entity.Event;
import com.consumerApp.entity.Session;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
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
