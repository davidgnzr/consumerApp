package com.consumerApp.kafka;

import com.consumerApp.entity.dto.SessionInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SessionProducer {
    //Kafka template created in KafkaProducerConfig for sending messages to kafka topics
    @Autowired
    private KafkaTemplate<String, SessionInputDto> sessionKafkaTemplate;
    @Value("${session.topic.name}")
    private String sessionTopic;

    public void send(SessionInputDto sessionInputDto) {
        sessionKafkaTemplate.send(sessionTopic, sessionInputDto);
    }
}
