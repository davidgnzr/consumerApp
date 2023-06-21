package com.consumerApp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
public class Event{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer eventId;

    private Date eventAt;
    private String eventType;
    private double numericEventValue;
}
