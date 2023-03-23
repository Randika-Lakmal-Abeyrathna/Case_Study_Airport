package com.randika.airportsystem.service;

import com.randika.airportsystem.dto.AirPlaneRequest;
import com.randika.airportsystem.entitie.Airplane;
import com.randika.airportsystem.entitie.Planetype;
import com.randika.airportsystem.exception.ResourceNotFound;
import com.randika.airportsystem.repository.AirplaneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AirplaneServiceImplUnitTest {


    @Mock
    private AirplaneRepository airplaneRepository;

    @Mock
    private  PlaneTypeServiceImpl planeTypeService;

    private AirplaneServiceImpl airplaneServiceTest;

    Airplane airplane;

    AirPlaneRequest airPlaneRequest;
    @BeforeEach
    void setUp() {

        airplaneServiceTest = new AirplaneServiceImpl(airplaneRepository,planeTypeService);

        airplane = Airplane.builder().id(1).
                planetype(Planetype.builder().id(1).model("ABC").capacity(150).weight(1500.0).status(true).build())
                .code("ABC").status(true).build();

        airPlaneRequest = AirPlaneRequest.builder().code("ABC").status(true).planetypeId(1).build();
    }

    @Test
    public void testCreateAirPlane(){
        when(airplaneRepository.save(any(Airplane.class))).thenReturn(airplane);

        Integer id = airplaneServiceTest.createAirplane(airPlaneRequest);

        assertThat(id).isEqualTo(airplane.getId());


    }

    @Test
    public void testGetAirPlaneById(){
        when(airplaneRepository.findById(1)).thenReturn(Optional.of(airplane));

        Airplane result = airplaneServiceTest.getAirplaneById(1);

        assertThat(result).isEqualTo(airplane);
    }

    @Test
    public void testGetAirPlaneByIdNotFound(){
        when(airplaneRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class, ()->{
            airplaneServiceTest.getAirplaneById(1);
        });

    }

    @Test
    public void testUpdateAirPlane(){
        when(airplaneRepository.findById(1)).thenReturn(Optional.of(airplane));
        when(airplaneRepository.save(any(Airplane.class))).thenReturn(airplane);

        AirPlaneRequest updateAirPlane = AirPlaneRequest.builder().code("XYZ").status(true).planetypeId(3).build();

        Airplane results = airplaneServiceTest.updateAirplane(1, updateAirPlane);

        assertThat(results.getCode()).isEqualTo(updateAirPlane.getCode());
        assertThat(results.isStatus()).isEqualTo(updateAirPlane.isStatus());


    }

    @Test
    public void testGetAllAirPlane(){
        when(airplaneRepository.findAll()).thenReturn(Arrays.asList(airplane));

        List<Airplane> results = airplaneServiceTest.getAllAirplane();

        assertThat(results).hasSize(1);
        assertThat(results.get(0)).isEqualTo(airplane);
    }

    @Test
    public void testGetAllActiveAirPlane(){
        when(airplaneRepository.findByStatus(true)).thenReturn(Arrays.asList(airplane));

        List<Airplane> results = airplaneServiceTest.getAllActiveAirplane();

        assertThat(results).hasSize(1);
        assertThat(results.get(0)).isEqualTo(airplane);
    }

    @Test
    public void testGetAllDeactivateAirPlane(){
        when(airplaneRepository.findByStatus(false)).thenReturn(Arrays.asList(airplane));

        List<Airplane> results = airplaneServiceTest.getAllDeactivateAirplane();

        assertThat(results).hasSize(1);
        assertThat(results.get(0)).isEqualTo(airplane);
    }



}