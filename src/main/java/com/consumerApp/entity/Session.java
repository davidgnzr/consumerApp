package com.consumerApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
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
