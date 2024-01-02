package com.consumerApp.controller;

import com.consumerApp.entity.dto.SessionInputDto;
import com.consumerApp.kafka.SessionProducer;
import com.consumerApp.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    SessionService sessionService;
    @Autowired
    SessionProducer sessionProducer;

    @GetMapping("{sessionId}")
    public ResponseEntity getSessionBySessionId(@PathVariable("sessionId") String sessionId) {
        try {
          return new ResponseEntity<>(sessionService.getBySessionId(sessionId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/recent")
    public ResponseEntity getRecentSession(@RequestParam("machineId") String machineId) {
        try {
            return new ResponseEntity<>(sessionService.getRecentSession(machineId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //PRODUCER: We send the session to kafka's topic
    @PostMapping
    public ResponseEntity sendSession(@RequestBody SessionInputDto sessionInputDto){
        try{
            sessionProducer.sendSession(sessionInputDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
