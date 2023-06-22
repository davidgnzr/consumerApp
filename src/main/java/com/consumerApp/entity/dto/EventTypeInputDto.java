package com.consumerApp.entity.dto;

import com.consumerApp.entity.Event;
import lombok.Data;

import java.util.Date;
@Data
public class EventTypeInputDto {
    private Date eventAt;
    private String eventType;
    private double numericEventValue;

    public Event toEvent(String sessionId){
        Event event=new Event();
        event.setEventAt(this.eventAt);
        event.setEventType(this.eventType);
        event.setNumericEventValue(this.numericEventValue);
        event.setSessionId(sessionId);
        return event;
    }
}
