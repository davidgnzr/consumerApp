package com.consumerApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Entity
@Getter
@Setter
public class Event{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer eventId;

    private Date eventAt;
    private String eventType;
    private double numericEventValue;
    private String sessionId;
}
