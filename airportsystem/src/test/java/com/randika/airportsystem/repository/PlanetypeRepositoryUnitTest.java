package com.randika.airportsystem.repository;

import com.randika.airportsystem.entitie.Planetype;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PlanetypeRepositoryUnitTest {

    @Autowired
    PlanetypeRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByStatusTest() {
        //Given
        Planetype planetype1 = Planetype.builder().model("test1").capacity(140).status(true).weight(150.5).build();
        Planetype planetype2 = Planetype.builder().model("test2").capacity(140).status(false).weight(150.5).build();
        Planetype planetype3 = Planetype.builder().model("test3").capacity(140).status(true).weight(150.5).build();

        underTest.saveAll(List.of(planetype1,planetype2,planetype3));

        // Call the findByStatus method with a true status
        List<Planetype> planeTypesWithStatusTrue = underTest.findByStatus(true);

        // Assert that the list contains only airplanes with true status
        assertEquals(2, planeTypesWithStatusTrue.size());
        assertTrue(planeTypesWithStatusTrue.stream().allMatch(planetype -> planetype.isStatus()));

    }
}