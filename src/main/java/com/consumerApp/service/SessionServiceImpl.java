package com.consumerApp.service;

import com.consumerApp.entity.Session;
import com.consumerApp.entity.dto.SessionOutputDto;
import com.consumerApp.repository.SessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    SessionRepository  sessionRepository;

    @Override
    public SessionOutputDto getBySessionId(String sessionId){
        Session session= sessionRepository.getBySessionId(sessionId).orElseThrow(EntityNotFoundException::new);
        return new SessionOutputDto(session);
    }
}
