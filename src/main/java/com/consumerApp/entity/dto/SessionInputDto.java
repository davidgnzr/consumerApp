package com.consumerApp.entity.dto;

import com.consumerApp.entity.Session;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
//To send this object via Kafka the class needs to be serializable.
public class SessionInputDto implements Serializable {

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
