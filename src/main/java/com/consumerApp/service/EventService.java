package com.consumerApp.service;

import com.consumerApp.entity.dto.EventOutputDto;

import java.util.List;

public interface EventService {
    List<EventOutputDto> getEventsBySessionId(String sessionId);
}
