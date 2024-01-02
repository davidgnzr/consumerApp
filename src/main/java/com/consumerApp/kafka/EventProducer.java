package com.consumerApp.kafka;

import com.consumerApp.entity.dto.EventInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventProducer {
    @Autowired
    private KafkaTemplate<String, EventInputDto> eventKafkaTemplate;
    @Value("${event.topic.name}")
    private String eventTopic;

    public void send(EventInputDto eventInputDto) {
        eventKafkaTemplate.send(eventTopic, eventInputDto);
    }
}
