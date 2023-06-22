package com.consumerApp.kafka;

import com.consumerApp.entity.dto.EventInputDto;
import com.consumerApp.entity.dto.EventTypeInputDto;
import com.consumerApp.entity.Session;
import com.consumerApp.repository.EventRepository;
import com.consumerApp.repository.SessionRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    SessionRepository sessionRepository;
    ObjectMapper objectMapper = new ObjectMapper();
    @KafkaListener(topics="${event.topic.name}", groupId = "${kafka.groupId.name}")
        //In charge of consuming sessions from the queue and storing them in the database.
    void eventListener(String event) throws JsonProcessingException {
        System.out.println("Event received: "+ event);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true); //For the object mapper to accept the list of events.
        EventInputDto eventInputDto=objectMapper.readValue(event,EventInputDto.class);

        //If the session exists in DB and is still open, the events are saved.
        if(!sessionRepository.getBySessionId(eventInputDto.getSessionID()).isEmpty()){
            Session session=sessionRepository.getBySessionId(eventInputDto.getSessionID()).get();
            if(session!=null && session.getEndDate()==null){
                for(EventTypeInputDto eventTypeInputDto:eventInputDto.getEvents()){
                    eventRepository.save(eventTypeInputDto.toEvent(session.getSessionId()));
                }
            }
        }
    }
}
