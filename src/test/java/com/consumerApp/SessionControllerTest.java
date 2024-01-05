package com.consumerApp;

import com.consumerApp.controller.SessionController;
import com.consumerApp.entity.dto.SessionInputDto;
import com.consumerApp.entity.dto.SessionOutputDto;
import com.consumerApp.kafka.SessionProducer;
import com.consumerApp.service.SessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SessionController.class)
public class SessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessionService sessionService;

    @MockBean
    private SessionProducer sessionProducer;

    private SessionOutputDto sessionOutputDto;
    private SessionInputDto sessionInputDto;

    @BeforeEach
    void setUp() {
        sessionOutputDto = new SessionOutputDto();
        sessionOutputDto.setSessionID("1");
        sessionOutputDto.setMachineId("5");
        sessionOutputDto.setCreatedDate(new Date());

        sessionInputDto = new SessionInputDto();
        sessionInputDto.setSessionID("1");
        sessionInputDto.setMachineId("5");
        sessionInputDto.setCreatedDate(new Date());
    }
    @Test
    void testGetSessionBySessionId() throws Exception{
        //Arrange-Act-Assert (AAA) Structure
        //Arrange
        String sessionId="1";
        when(sessionService.getBySessionId(sessionId)).thenReturn(sessionOutputDto);

        //Act and Assert
        mockMvc.perform(get("/session/{sessionId}", sessionId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        //Verify that the service method was called with the correct argument
        verify(sessionService).getBySessionId(sessionId);
    }

    @Test
    void testGetSessionBySessionIdNotFound() throws Exception {
        // Arrange
        String sessionId = "X";
        when(sessionService.getBySessionId(sessionId)).thenThrow(new EntityNotFoundException());

        // Act and Assert
        mockMvc.perform(get("/session/" + sessionId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetRecentSession() throws Exception {
        //Arrange
        String machineId = "5";
        when(sessionService.getRecentSession(machineId)).thenReturn(sessionOutputDto);

        //Act and Assert
        mockMvc.perform(get("/session/recent").param("machineId", machineId))
                .andExpect(status().isOk());

        //Verify
        verify(sessionService).getRecentSession(machineId);
    }

    @Test
    void testGetRecentSessionNotFound() throws Exception {
        // Arrange
        String machineId = "X";
        when(sessionService.getRecentSession(machineId)).thenThrow(new EntityNotFoundException());

        // Act and Assert
        mockMvc.perform(get("/session/recent").param("machineId", machineId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSendSession() throws Exception {
        // Arrange
        //When the send method of sessionProducer is called with the provided sessionInputDto, do nothing.
        //Commonly used in void methods.
        doNothing().when(sessionProducer).send(sessionInputDto);

        //To convert an object to JSON string
        ObjectMapper objectMapper = new ObjectMapper();

        // Act and Assert
        mockMvc.perform(post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sessionInputDto)))
                .andExpect(status().isOk());

        // Verify that the producer method was called with the correct argument
        verify(sessionProducer).send(sessionInputDto);
    }

    @Test
    void testSendSessionConflict() throws Exception {
        // Arrange
        doThrow(new RuntimeException()).when(sessionProducer).send(sessionInputDto);

        //To convert an object to JSON string
        ObjectMapper objectMapper = new ObjectMapper();

        // Act and Assert
        mockMvc.perform(post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sessionInputDto)))
                .andExpect(status().isConflict());

        // Verify that the producer method was called with the correct argument
        verify(sessionProducer).send(sessionInputDto);
    }
}
