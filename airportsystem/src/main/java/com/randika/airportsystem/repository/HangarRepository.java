package com.randika.airportsystem.repository;

import com.randika.airportsystem.entitie.Hangar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HangarRepository extends JpaRepository<Hangar,Integer> {
}
