package com.consumerApp.service;

import com.consumerApp.entity.dto.SessionOutputDto;
import org.springframework.stereotype.Service;


public interface SessionService {
    SessionOutputDto getBySessionId(String sessionId);
}
