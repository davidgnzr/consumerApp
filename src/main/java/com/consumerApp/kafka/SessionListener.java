package com.consumerApp.kafka;

import com.consumerApp.entity.Session;
import com.consumerApp.entity.dto.SessionInputDto;
import com.consumerApp.repository.SessionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class SessionListener {
    @Autowired
    SessionRepository sessionRepository;

    //In charge of consuming sessions from the queue and storing them in the database.
    @KafkaListener(topics="${session.topic.name}", groupId = "${kafka.groupId.name}")
    void sessionListener(SessionInputDto sessionInputDto) throws JsonProcessingException {
        System.out.println("Session received: "+ sessionInputDto);
        //The open session is fetched to close it when the new one is saved.
        Optional<Session> closedSessionOpt=sessionRepository.getByEndDate(null);
        sessionRepository.save(sessionInputDto.toSession());
        if(!closedSessionOpt.isEmpty()){
            Session closedSession=closedSessionOpt.get();
            closedSession.setEndDate(new Date());
            sessionRepository.save(closedSession);
        }
    }
}
