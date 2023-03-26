package com.randika.airportsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.randika.airportsystem.dto.AirPlaneRequest;
import com.randika.airportsystem.dto.HangarRequest;
import com.randika.airportsystem.entitie.Airplane;
import com.randika.airportsystem.entitie.Hangar;
import com.randika.airportsystem.entitie.Planetype;
import com.randika.airportsystem.exception.ResourceNotFound;
import com.randika.airportsystem.service.HangarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class HangarControllerIntegrationTest {

    @Mock
    private HangarServiceImpl hangarService;

    private HangarController hangarControllerTest;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        hangarControllerTest = new HangarController(hangarService);
        mockMvc= MockMvcBuilders.standaloneSetup(hangarControllerTest).build();

    }

    @Test
    void createHangar_shouldReturnCreatedStatus() throws Exception{
        HangarRequest  request= new HangarRequest();
        request.setLocation("ABC12");
        request.setStatus(true);
        request.setAirplaneId(1);

        Integer expectedId=1;

        when(hangarService.createHangar(request)).thenReturn(expectedId);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/hangar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$",is(expectedId)));

    }

    @Test
    void getHangerById_shouldReturnHangar()throws Exception{
        Integer id=1;
        Hangar expectedHangar = new Hangar();
        expectedHangar.setId(1);
        expectedHangar.setStatus(true);
        expectedHangar.setLocation("ABC1234");
        expectedHangar.setAirplane(new Airplane());

        when(hangarService.getHangarById(1)).thenReturn(expectedHangar);

        mockMvc.perform(get("/api/v1/hangar/{id}",id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(id)))
                .andExpect(jsonPath("$.location",is(expectedHangar.getLocation())))
                .andExpect(jsonPath("$.status",is(expectedHangar.isStatus())))
                .andExpect(jsonPath("$.airplane.id",is(expectedHangar.getAirplane().getId())));

    }

    @Test
    void getHangarById_shouldReturnNotFound() throws Exception{
        Integer id= 1;

        given(hangarService.getHangarById(id)).willThrow(new ResourceNotFound("Hangar not found with id: "+id));

        mockMvc.perform(get("/api/v1/hangar/{id}",1))
                .andExpect(status().isNotFound());

    }

    @Test
    void getAllHangar_shouldReturnListOfHangars() throws Exception{

        List<Hangar> expectedHangars = Arrays.asList(
                new Hangar(1, "ABC123", true, new Airplane()),
                new Hangar(2, "DEF456", false, new Airplane())
        );

        when(hangarService.getAllHangars()).thenReturn(expectedHangars);

        mockMvc.perform(get("/api/v1/hangar/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(expectedHangars.get(0).getId())))
                .andExpect(jsonPath("$[0].location", is(expectedHangars.get(0).getLocation())))
                .andExpect(jsonPath("$[0].status", is(expectedHangars.get(0).isStatus())))
                .andExpect(jsonPath("$[0].airplane.id", is(expectedHangars.get(0).getAirplane().getId())))
                .andExpect(jsonPath("$[1].id", is(expectedHangars.get(1).getId())))
                .andExpect(jsonPath("$[1].location", is(expectedHangars.get(1).getLocation())))
                .andExpect(jsonPath("$[1].status", is(expectedHangars.get(1).isStatus())))
                .andExpect(jsonPath("$[1].airplane.id", is(expectedHangars.get(1).getAirplane().getId())));
    }

    @Test
    void updateHangar_shouldReturnValidHangarDetails() throws Exception{
        Integer id =1;
        HangarRequest request = new HangarRequest();
        request.setLocation("XYZ890");
        request.setAirplaneId(2);
        request.setStatus(false);

        Hangar expectedHangar = new Hangar();
        expectedHangar.setId(id);
        expectedHangar.setLocation(request.getLocation());
        expectedHangar.setStatus(request.isStatus());
        expectedHangar.setAirplane(new Airplane());

        given(hangarService.updareHangar(id,request)).willReturn(expectedHangar);

        mockMvc.perform(put("/api/v1/hangar/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.location", is(request.getLocation())))
                .andExpect(jsonPath("$.status", is(request.isStatus())))
                .andExpect(jsonPath("$.airplane", notNullValue()));

    }

    @Test
    void updateHangar_shouldReturnNotFound() throws Exception{
        Integer id =1;
        HangarRequest request = new HangarRequest();
        request.setLocation("XYZ890");
        request.setAirplaneId(2);
        request.setStatus(false);


        given(hangarService.updareHangar(id,request)).willThrow(new ResourceNotFound("Hangar not found with id: "+id));

        mockMvc.perform(put("/api/v1/hangar/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isNotFound());


    }

    @Test
    void getAllActiveHangar_shouldReturnAllActiveHangars() throws Exception{
        Hangar hangar1 = new Hangar(1, "ABC123", true, new Airplane());
        Hangar hangar2 = new Hangar(2, "DEF456", true, new Airplane());
        List<Hangar> expectedHangars = Arrays.asList(
                hangar1,hangar2
        );

        given(hangarService.getAllActiveHangars()).willReturn(expectedHangars);

        mockMvc.perform(get("/api/v1/hangar/active"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(hangar1.getId()))
                .andExpect(jsonPath("$[0].location").value(hangar1.getLocation()))
                .andExpect(jsonPath("$[0].status").value(hangar1.isStatus()))
                .andExpect(jsonPath("$[1].id").value(hangar2.getId()))
                .andExpect(jsonPath("$[1].location").value(hangar2.getLocation()))
                .andExpect(jsonPath("$[1].status").value(hangar2.isStatus()));

    }

    @Test
    void getAllDeactivateHangar_shouldReturnAllDeactivateHangars() throws Exception{
        Hangar hangar1 = new Hangar(1, "ABC123", false, new Airplane());
        Hangar hangar2 = new Hangar(2, "DEF456", false, new Airplane());
        List<Hangar> expectedHangars = Arrays.asList(
                hangar1,hangar2
        );

        given(hangarService.getAllDeactivateHangars()).willReturn(expectedHangars);

        mockMvc.perform(get("/api/v1/hangar/deactivate"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(hangar1.getId()))
                .andExpect(jsonPath("$[0].location").value(hangar1.getLocation()))
                .andExpect(jsonPath("$[0].status").value(hangar1.isStatus()))
                .andExpect(jsonPath("$[1].id").value(hangar2.getId()))
                .andExpect(jsonPath("$[1].location").value(hangar2.getLocation()))
                .andExpect(jsonPath("$[1].status").value(hangar2.isStatus()));

    }



}