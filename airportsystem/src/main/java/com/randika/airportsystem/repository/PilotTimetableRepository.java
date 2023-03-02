package com.randika.airportsystem.repository;


import com.randika.airportsystem.entitie.PilotTimetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PilotTimetableRepository extends JpaRepository<PilotTimetable,Integer> {
}
