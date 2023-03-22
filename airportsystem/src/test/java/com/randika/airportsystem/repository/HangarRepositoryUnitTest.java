package com.randika.airportsystem.repository;

import com.randika.airportsystem.entitie.Airplane;
import com.randika.airportsystem.entitie.Hangar;
import com.randika.airportsystem.entitie.Planetype;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class HangarRepositoryUnitTest {

    @Autowired
    private HangarRepository underTest;

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private PlanetypeRepository planetypeRepository;



    @AfterEach
    void tearDown() {
        planetypeRepository.deleteAll();
        airplaneRepository.deleteAll();
        underTest.deleteAll();
    }

    @Test
    void findByStatusTest(){
        // Given
        Planetype planetype = Planetype.builder().model("test").capacity(140).status(false).weight(150.5).build();
        planetypeRepository.save(planetype);
        Airplane airplane = Airplane.builder().code("ABC").planetype(planetype).status(true).build();
        airplaneRepository.save(airplane);
        Hangar hangar1 = Hangar.builder().airplane(airplane).status(true).location("A1").build();
        Hangar hangar2 = Hangar.builder().airplane(airplane).status(false).location("A2").build();
        Hangar hangar3 = Hangar.builder().airplane(airplane).status(true).location("A13").build();
        underTest.saveAll(List.of(hangar1,hangar2,hangar3));

        // When
        List<Hangar> hangarsWithStatusTrue = underTest.findByStatus(true);
        // Then
        assertEquals(2,hangarsWithStatusTrue.size());
        assertTrue(hangarsWithStatusTrue.stream().allMatch(hanger -> hanger.isStatus()));
    }


}