package com.randika.airportsystem.service;

import com.randika.airportsystem.entitie.Pilot;
import com.randika.airportsystem.exception.ResourceNotFound;
import com.randika.airportsystem.repository.PilotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PilotServiceImplUnitTest {

    @Mock
    private PilotRepository pilotRepository;

    private PilotServiceImpl pilotServiceTest;

    Pilot pilot;

    @BeforeEach
    void setUp() {
        pilotServiceTest= new PilotServiceImpl(pilotRepository);

        pilot= Pilot.builder().firstname("Randika").lastname("lakmal").id(1).licence("ABC123").build();
    }


    @Test
    public void testCreatePilot(){
        when(pilotRepository.save(any(Pilot.class))).thenReturn(pilot);

        Integer id = pilotServiceTest.createPilot(pilot);

        assertThat(id).isEqualTo(pilot.getId());
    }

    @Test
    public void testGetPilotById(){
        when(pilotRepository.findById(1)).thenReturn(Optional.of(pilot));

        Pilot result = pilotServiceTest.getPilotById(1);

        assertThat(result).isEqualTo(pilot);

    }

    @Test
    public void testGetPilotByIdNotFound(){
        when(pilotRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class,()->{
            pilotServiceTest.getPilotById(1);
        });


    }

    @Test
    public void testUpdatePilot(){
        when(pilotRepository.findById(1)).thenReturn(Optional.of(pilot));
        when(pilotRepository.save(any(Pilot.class))).thenReturn(pilot);

        Pilot updatePilot = Pilot.builder().licence("ABC12345").lastname("Test").firstname("CAT").id(1).build();

        Pilot result = pilotServiceTest.updatePilot(1, updatePilot);

        assertThat(result).isEqualTo(updatePilot);

    }

    @Test
    public void testGetAllPilot(){
        when(pilotRepository.findAll()).thenReturn(Arrays.asList(pilot));

        List<Pilot> result = pilotServiceTest.getAllPilots();
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(pilot);
    }


}