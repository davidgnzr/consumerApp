package com.consumerApp.service;

import com.consumerApp.entity.Event;
import com.consumerApp.entity.Session;
import com.consumerApp.entity.dto.EventOutputDto;
import com.consumerApp.entity.dto.SessionOutputDto;
import com.consumerApp.repository.EventRepository;
import com.consumerApp.repository.SessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    SessionRepository  sessionRepository;

    @Autowired
    EventRepository eventRepository;

    @Override
    public SessionOutputDto getBySessionId(String sessionId){
        Session session= sessionRepository.getBySessionId(sessionId).orElseThrow(EntityNotFoundException::new);
        return new SessionOutputDto(session);
    }

    @Override
    public SessionOutputDto getRecentSession(String machineId) {
        boolean first=true;
        Session recentSession = null;
        
        List<Session> sessionList= sessionRepository.getByMachineId(machineId).orElseThrow(EntityNotFoundException::new);
        if(sessionList.isEmpty())
            throw (new EntityNotFoundException());

        for(Session session:sessionList){
            if(first){
                recentSession=session;
                first=false;
            }
            if(session.getCreatedDate().after(recentSession.getCreatedDate()))
                recentSession=session;
        }
        return new SessionOutputDto(recentSession);
    }
}
