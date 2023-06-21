package com.consumerApp.service;

import com.consumerApp.entity.dto.EventOutputDto;
import com.consumerApp.entity.dto.SessionOutputDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SessionService {
    SessionOutputDto getBySessionId(String sessionId);
    SessionOutputDto getRecentSession(String machineId);
}
