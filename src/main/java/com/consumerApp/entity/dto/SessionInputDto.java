package com.consumerApp.entity.dto;

import com.consumerApp.entity.Session;
import lombok.Data;

import java.util.Date;
@Data
public class SessionInputDto {
    private String sessionID;
    private String machineId;
    private Date createdDate;

    public Session toSession(){
        Session session=new Session();
        session.setSessionId(this.sessionID);
        session.setMachineId(this.machineId);
        session.setCreatedDate(this.createdDate);
        return session;
    }
}
