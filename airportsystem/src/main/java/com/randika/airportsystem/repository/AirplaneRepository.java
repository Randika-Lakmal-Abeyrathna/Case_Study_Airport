package com.randika.airportsystem.repository;


import com.randika.airportsystem.entitie.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane,Integer> {

    List<Airplane> findByStatus(boolean status);
}
