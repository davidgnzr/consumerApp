package com.consumerApp.controller;

import com.consumerApp.entity.dto.EventInputDto;
import com.consumerApp.kafka.EventProducer;
import com.consumerApp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventService eventService;
    @Autowired
    EventProducer eventProducer;

    @GetMapping
    public ResponseEntity<?> getEventsBySessionId(@RequestParam("sessionId") String sessionId) {
        try {
            return new ResponseEntity<>(eventService.getEventsBySessionId(sessionId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //PRODUCER: We send the event to kafka's topic
    @PostMapping
    public ResponseEntity sendEvent(@RequestBody EventInputDto eventInputDto){
        try{
            eventProducer.send(eventInputDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
