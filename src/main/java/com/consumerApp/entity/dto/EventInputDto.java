package com.consumerApp.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class EventInputDto implements Serializable {
    private String sessionID;
    private List<EventTypeInputDto> events;

}
