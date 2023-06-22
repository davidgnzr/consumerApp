package com.consumerApp.entity.dto;

import lombok.Data;

import java.util.List;
@Data
public class EventInputDto {
    private String sessionID;
    private List<EventTypeInputDto> events;

}
