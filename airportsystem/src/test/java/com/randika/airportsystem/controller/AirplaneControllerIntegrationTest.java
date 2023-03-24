package com.randika.airportsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.randika.airportsystem.dto.AirPlaneRequest;
import com.randika.airportsystem.service.AirplaneServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
class AirplaneControllerIntegrationTest {

    @Mock
    private AirplaneServiceImpl airplaneService;

    private AirplaneController airplaneControllerTest;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        airplaneControllerTest = new AirplaneController(airplaneService);
        mockMvc = MockMvcBuilders.standaloneSetup(airplaneControllerTest).build();
    }

    @Test
    void createAirplane_shouldReturnCreatedStatus() throws Exception{
        AirPlaneRequest request= new AirPlaneRequest();
        request.setCode("ABC123");
        request.setStatus(true);
        request.setPlanetypeId(1);

        Integer expectedId=1;

        when(airplaneService.createAirplane(request)).thenReturn(expectedId);

        mockMvc.perform(post("/api/v1/airplane")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", is(expectedId)));


    }
}