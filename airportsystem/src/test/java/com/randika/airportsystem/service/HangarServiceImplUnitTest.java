package com.randika.airportsystem.service;

import com.randika.airportsystem.dto.HangarRequest;
import com.randika.airportsystem.entitie.Airplane;
import com.randika.airportsystem.entitie.Hangar;
import com.randika.airportsystem.exception.ResourceNotFound;
import com.randika.airportsystem.repository.HangarRepository;
import org.assertj.core.api.AssertionsForClassTypes;
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
class HangarServiceImplUnitTest {

    @Mock
    private HangarRepository hangarRepository;

    @Mock
    private AirplaneServiceImpl airplaneService;

    private HangarServiceImpl hangarServiceTest;

    Hangar hangar;

    HangarRequest hangarRequest;

    @BeforeEach
    void setUp() {

        hangarServiceTest = new HangarServiceImpl(airplaneService,hangarRepository);

        hangar = Hangar.builder().id(1).location("Test").status(true)
                .airplane(new Airplane()).build();



    }


    @Test
    public void testCreateHangar(){
        when(hangarRepository.save(any(Hangar.class))).thenReturn(hangar);
        hangarRequest = HangarRequest.builder().location("ABC").status(true).airplaneId(1).build();

        Integer id = hangarServiceTest.createHangar(hangarRequest);

        assertThat(id).isEqualTo(hangar.getId());
    }


    @Test
    public void testGetHangarById(){
        when(hangarRepository.findById(1)).thenReturn(Optional.of(hangar));

        Hangar result = hangarServiceTest.getHangarById(1);

        assertThat(result).isEqualTo(hangar);
    }

    @Test
    public void testGetHangerByIdNotFound(){
        when(hangarRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class,()-> {
            hangarServiceTest.getHangarById(1);
        });

    }

    @Test
    public void testUpdateHangar(){
        when(hangarRepository.findById(1)).thenReturn(Optional.of(hangar));
        when(hangarRepository.save(any(Hangar.class))).thenReturn(hangar);

        hangarRequest = HangarRequest.builder().location("ABC").status(true).airplaneId(1).build();

        Hangar result = hangarServiceTest.updareHangar(1, hangarRequest);

        assertThat(hangar).isEqualTo(result);
    }

    @Test
    public void testGetAllHangar(){
        when(hangarRepository.findAll()).thenReturn(Arrays.asList(hangar));

        List<Hangar> result = hangarServiceTest.getAllHangars();

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(hangar);
    }

    @Test
    public void testGetAllActiveHangar(){
        when(hangarRepository.findByStatus(true)).thenReturn(Arrays.asList(hangar));

        List<Hangar> result = hangarServiceTest.getAllActiveHangars();

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(hangar);
    }

    @Test
    public void testGetAllDeactivateHangar(){
        when(hangarRepository.findByStatus(false)).thenReturn(Arrays.asList(hangar));

        List<Hangar> result = hangarServiceTest.getAllDeactivateHangars();

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(hangar);
    }

}