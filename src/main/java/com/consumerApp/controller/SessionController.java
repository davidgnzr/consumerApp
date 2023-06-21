package com.consumerApp.controller;

import com.consumerApp.entity.dto.SessionOutputDto;
import com.consumerApp.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    SessionService sessionService;

    @GetMapping("{sessionId}")
    public ResponseEntity<?> getPersonasById(@PathVariable("sessionId") String sessionId) {
        try {
          return new ResponseEntity<>(sessionService.getBySessionId(sessionId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
