package com.consumerApp.service;

import com.consumerApp.entity.Event;
import com.consumerApp.entity.Session;
import com.consumerApp.entity.dto.EventOutputDto;
import com.consumerApp.repository.SessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    @Autowired
    SessionRepository sessionRepository;
    @Override
    public List<EventOutputDto> getEventsBySessionId(String sessionId) {
        Session session= sessionRepository.getBySessionId(sessionId).orElseThrow(EntityNotFoundException::new);
        List<EventOutputDto> eventOutputDtoList= new ArrayList<>();
        if(!session.getEvents().isEmpty())
            for(Event event:session.getEvents()){
                eventOutputDtoList.add(new EventOutputDto(event));
            }
        return eventOutputDtoList;
    }
}
