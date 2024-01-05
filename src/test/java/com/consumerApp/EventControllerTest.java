package com.consumerApp;

import com.consumerApp.controller.EventController;
import com.consumerApp.entity.dto.EventOutputDto;
import com.consumerApp.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//To extend the features of JUnit 5 by enabling the features of Mockito
@ExtendWith(MockitoExtension.class)
public class EventControllerTest {
    @Mock
    private EventService eventService;
    @InjectMocks  //To inject the previous mocks into the controller
    private EventController eventController;
    private EventOutputDto eventOutputDto1;
    private EventOutputDto eventOutputDto2;

    @BeforeEach
    void setUp() {
        eventOutputDto1 = new EventOutputDto();
        eventOutputDto1.setEventAt(new Date());
        eventOutputDto1.setEventType("EventType1Test");
        eventOutputDto1.setNumericEventValue(85);

        eventOutputDto2 = new EventOutputDto();
        eventOutputDto2.setEventAt(new Date());
        eventOutputDto2.setEventType("EventType2Test");
        eventOutputDto2.setNumericEventValue(65.5);
    }

    @Test
    void getEventsBySessionIdTest() {
        //Arrange-Act-Assert (AAA) Structure
        //Given - Arrange
        String sessionId="1";
        List<EventOutputDto> events =new ArrayList<>();
        events.add(eventOutputDto1);
        events.add(eventOutputDto2);
        when(eventService.getEventsBySessionId(sessionId)).thenReturn(events);

        //When - Act
        ResponseEntity<?> responseEntity = eventController.getEventsBySessionId(sessionId);

        //Then - Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
