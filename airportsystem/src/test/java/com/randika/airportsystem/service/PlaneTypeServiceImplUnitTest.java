package com.randika.airportsystem.service;

import com.randika.airportsystem.entitie.Planetype;
import com.randika.airportsystem.exception.ResourceNotFound;
import com.randika.airportsystem.repository.PlanetypeRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PlaneTypeServiceImplUnitTest {

    @Mock
    private PlanetypeRepository planetypeRepository;


    private PlaneTypeServiceImpl planetypeService;

    Planetype planetype = Planetype.builder()
            .id(1)
            .model("Boeing 747")
            .capacity(416)
            .weight(396000.0)
            .status(true)
            .build();


    @BeforeEach
    void setUp() {
        planetypeService = new PlaneTypeServiceImpl(planetypeRepository);

        planetype = Planetype.builder()
                .id(1)
                .model("Boeing 747")
                .capacity(416)
                .weight(396000.0)
                .status(true)
                .build();
    }

    @Test
    public void testCreatePlaneType(){

        when(planetypeRepository.save(planetype)).thenReturn(planetype);

        Integer id = planetypeService.createPlaneType(planetype);

        assertThat(id).isEqualTo(planetype.getId());
    }

    @Test
    public void testGetPlaneTypeById(){
        when(planetypeRepository.findById(1)).thenReturn(Optional.of(planetype));

        Planetype result = planetypeService.getPlaneTypeByID(1);

        assertThat(result).isEqualTo(planetype);


    }

    @Test
    public void testGetPlaneTypeByIdNotFound(){
        when(planetypeRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class, () ->{
            planetypeService.getPlaneTypeByID(1);
        });
    }

    @Test
    public void testUpdatePlaneType(){
        when(planetypeRepository.findById(1)).thenReturn(Optional.of(planetype));
        when(planetypeRepository.save(any(Planetype.class))).thenReturn(planetype);
        Planetype updatedPlane = Planetype.builder().id(1).model("ABC").capacity(835).weight(56000.0).status(false).build();

        Planetype result = planetypeService.updatePlanetype(1,updatedPlane);
        assertThat(result).isEqualTo(updatedPlane);
    }

    @Test
    public void testGetAllPlaneType(){
        when(planetypeRepository.findAll()).thenReturn(Arrays.asList(planetype));
        List<Planetype> results = planetypeService.getAllPlaneType();

        assertThat(results).hasSize(1);
        assertThat(results.get(0)).isEqualTo(planetype);
    }

    @Test
    public void testGetAllActivePlaneType(){
        when(planetypeRepository.findByStatus(true)).thenReturn(Arrays.asList(planetype));

        List<Planetype> results = planetypeService.getAllActivePlaneType();

        assertThat(results).hasSize(1);
        assertThat(results.get(0)).isEqualTo(planetype);
    }

    @Test
    public void testGetAllDeactivatePlaneType(){
        when(planetypeRepository.findByStatus(false)).thenReturn(Arrays.asList(planetype));

        List<Planetype> results = planetypeService.getAllDeactivatePlaneType();

        assertThat(results).hasSize(1);
        assertThat(results.get(0)).isEqualTo(planetype);
    }

}