package com.randika.airportsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.randika.airportsystem.dto.AirPlaneRequest;
import com.randika.airportsystem.entitie.Airplane;
import com.randika.airportsystem.entitie.Planetype;
import com.randika.airportsystem.exception.ResourceNotFound;
import com.randika.airportsystem.service.AirplaneServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    void getAirplaneById_shouldReturnAirplane() throws Exception{
        Integer id =1;
        Airplane expectedAirplane = new Airplane();
        expectedAirplane.setId(id);
        expectedAirplane.setCode("ABC1234");
        expectedAirplane.setStatus(true);
        expectedAirplane.setPlanetype(new Planetype());

        when(airplaneService.getAirplaneById(id)).thenReturn(expectedAirplane);

        mockMvc.perform(get("/api/v1/airplane/{id}",id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(id)))
                .andExpect(jsonPath("$.code",is(expectedAirplane.getCode())))
                .andExpect(jsonPath("$.status",is(expectedAirplane.isStatus())))
                .andExpect(jsonPath("$.planetype.id",is(expectedAirplane.getPlanetype().getId())));
    }

    @Test
    void getAirplaneById_shouldReturnNotFound() throws Exception{
        Integer id =1;

        given(airplaneService.getAirplaneById(id)).willThrow(new ResourceNotFound("Airplane not found with id: " +id));

        mockMvc.perform(get("/api/v1/airplane/{id}",id))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllAirplane_shouldReturnListOfAirplanes() throws Exception{

        List<Airplane> expectedAirplanes = Arrays.asList(
                new Airplane(1, "ABC123", new Planetype(), true),
                new Airplane(2, "DEF456", new Planetype(), false)
        );

        when(airplaneService.getAllAirplane()).thenReturn(expectedAirplanes);

        mockMvc.perform(get("/api/v1/airplane/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(expectedAirplanes.get(0).getId())))
                .andExpect(jsonPath("$[0].code", is(expectedAirplanes.get(0).getCode())))
                .andExpect(jsonPath("$[0].status", is(expectedAirplanes.get(0).isStatus())))
                .andExpect(jsonPath("$[0].planetype.id", is(expectedAirplanes.get(0).getPlanetype().getId())))
                .andExpect(jsonPath("$[1].id", is(expectedAirplanes.get(1).getId())))
                .andExpect(jsonPath("$[1].code", is(expectedAirplanes.get(1).getCode())))
                .andExpect(jsonPath("$[1].status", is(expectedAirplanes.get(1).isStatus())))
                .andExpect(jsonPath("$[1].planetype.id", is(expectedAirplanes.get(1).getPlanetype().getId())));
    }

    @Test
    void updateAirplane_shouldReturnValidAirplaneDetails() throws Exception{
        Integer id =1;
        AirPlaneRequest request = new AirPlaneRequest();
        request.setCode("XYZ890");
        request.setPlanetypeId(2);
        request.setStatus(false);

        Airplane expectedAirplane = new Airplane();
        expectedAirplane.setId(id);
        expectedAirplane.setCode(request.getCode());
        expectedAirplane.setStatus(request.isStatus());
        expectedAirplane.setPlanetype(new Planetype());

        given(airplaneService.updateAirplane(id,request)).willReturn(expectedAirplane);

        mockMvc.perform(put("/api/v1/airplane/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.code", is(request.getCode())))
                .andExpect(jsonPath("$.status", is(request.isStatus())))
                .andExpect(jsonPath("$.planetype", notNullValue()));

    }

    @Test
    void updateAirplane_shouldReturnNotFound() throws Exception{
        Integer id =1;
        AirPlaneRequest request = new AirPlaneRequest();
        request.setCode("XYZ890");
        request.setPlanetypeId(2);
        request.setStatus(false);


        given(airplaneService.updateAirplane(id,request)).willThrow(new ResourceNotFound("Airplane not found with id: "+id));

        mockMvc.perform(put("/api/v1/airplane/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isNotFound());


    }

    @Test
    void getAllActiveAirplane_shouldReturnAllActiveAirplanes() throws Exception{
        Airplane airplane1 = new Airplane(1, "ABC123", new Planetype(), true);
        Airplane airplane2 = new Airplane(2, "DEF456", new Planetype(), true);
        List<Airplane> expectedAirplanes = Arrays.asList(
                airplane1,airplane2
        );

        given(airplaneService.getAllActiveAirplane()).willReturn(expectedAirplanes);

        mockMvc.perform(get("/api/v1/airplane/active"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(airplane1.getId()))
                .andExpect(jsonPath("$[0].code").value(airplane1.getCode()))
                .andExpect(jsonPath("$[0].status").value(airplane1.isStatus()))
                .andExpect(jsonPath("$[1].id").value(airplane2.getId()))
                .andExpect(jsonPath("$[1].code").value(airplane2.getCode()))
                .andExpect(jsonPath("$[1].status").value(airplane2.isStatus()));

    }

    @Test
    void getAllDeactivateAirplane_shouldReturnAllDeactivateAirplanes() throws Exception{
        Airplane airplane1 = new Airplane(1, "ABC123", new Planetype(), false);
        Airplane airplane2 = new Airplane(2, "DEF456", new Planetype(), false);
        List<Airplane> expectedAirplanes = Arrays.asList(
                airplane1,airplane2
        );

        given(airplaneService.getAllDeactivateAirplane()).willReturn(expectedAirplanes);

        mockMvc.perform(get("/api/v1/airplane/deactivate"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(airplane1.getId()))
                .andExpect(jsonPath("$[0].code").value(airplane1.getCode()))
                .andExpect(jsonPath("$[0].status").value(airplane1.isStatus()))
                .andExpect(jsonPath("$[1].id").value(airplane2.getId()))
                .andExpect(jsonPath("$[1].code").value(airplane2.getCode()))
                .andExpect(jsonPath("$[1].status").value(airplane2.isStatus()));

    }




















}