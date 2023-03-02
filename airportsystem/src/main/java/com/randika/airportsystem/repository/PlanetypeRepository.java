package com.randika.airportsystem.repository;


import com.randika.airportsystem.entitie.Planetype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanetypeRepository extends JpaRepository<Planetype,Integer> {

    Optional<Planetype> findById(Integer id);

}
