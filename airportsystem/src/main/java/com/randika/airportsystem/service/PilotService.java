package com.randika.airportsystem.service;

import com.randika.airportsystem.entitie.Pilot;

import java.util.List;

public interface PilotService {

    Integer createPilot(Pilot pilot);

    Pilot getPilotById(Integer id);

    Pilot updatePilot(Integer id, Pilot pilot);

    List<Pilot> getAllPilots();

    

}
