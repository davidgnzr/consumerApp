package com.consumerApp.entity.dto;

import com.consumerApp.entity.Event;
import com.consumerApp.entity.Session;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class EventOutputDto {
    
    private Date eventAt;
    private String eventType;
    private double numericEventValue;
    public EventOutputDto(Event event){
        this.eventAt=event.getEventAt();
        this.eventType=event.getEventType();
        this.numericEventValue=event.getNumericEventValue();
    }
}
