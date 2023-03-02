package com.randika.airportsystem.repository;


import com.randika.airportsystem.entitie.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PilotRepository extends JpaRepository<Pilot,Integer> {
}
