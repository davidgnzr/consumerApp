package com.consumerApp.kafka;

import com.consumerApp.entity.dto.SessionInputDto;
import com.consumerApp.repository.SessionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SessionListener {
    @Autowired
    SessionRepository sessionRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics="${session.topic.name}", groupId = "${kafka.groupId.name}")
        //In charge of consuming sessions from the queue and storing them in the database.
    void sessionListener(String session) throws JsonProcessingException {
        System.out.println("Session received: "+ session);
        SessionInputDto sessionInputDto=objectMapper.readValue(session,SessionInputDto.class);
        sessionRepository.save(sessionInputDto.toSession());
    }
}
