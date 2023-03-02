package com.randika.airportsystem.repository;


import com.randika.airportsystem.entitie.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane,Integer> {
}
