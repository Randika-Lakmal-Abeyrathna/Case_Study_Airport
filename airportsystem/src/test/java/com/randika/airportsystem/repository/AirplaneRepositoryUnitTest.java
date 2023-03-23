package com.randika.airportsystem.repository;

import com.randika.airportsystem.entitie.Airplane;
import com.randika.airportsystem.entitie.Planetype;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AirplaneRepositoryUnitTest {

    @Autowired
    private AirplaneRepository underTest;

    @Autowired
    private PlanetypeRepository planetypeRepositoryUnderTest;

    @AfterEach
    void tearDown() {
        planetypeRepositoryUnderTest.deleteAll();
        underTest.deleteAll();
    }

    @Test
    void findByStatusTest() {
        //Given
        Planetype planetype = Planetype.builder().model("test").capacity(140).status(true).weight(150.5).build();
        planetypeRepositoryUnderTest.save(planetype);
        Airplane airplane1 = Airplane.builder().code("ABC").planetype(planetype).status(true).build();
        Airplane airplane2 = Airplane.builder().code("DEF").planetype(planetype).status(false).build();
        Airplane airplane3 = Airplane.builder().code("GHI").planetype(planetype).status(true).build();
        underTest.saveAll(List.of(airplane1, airplane2, airplane3));

        // Call the findByStatus method with a true status
        List<Airplane> airplanesWithStatusTrue = underTest.findByStatus(true);

        // Assert that the list contains only airplanes with true status
        assertEquals(2, airplanesWithStatusTrue.size());
        assertTrue(airplanesWithStatusTrue.stream().allMatch(airplane -> airplane.isStatus()));

    }
}