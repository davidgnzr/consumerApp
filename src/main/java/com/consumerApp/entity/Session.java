package com.consumerApp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Session {
    @Id
    private String sessionId;

    private String machineId;
    private Date createdDate;
    @OneToMany
    @JoinColumn(name= "sessionId")
    private List<Event> events;
    private Date endDate;
}
