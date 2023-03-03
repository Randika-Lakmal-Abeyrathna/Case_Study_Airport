package com.randika.airportsystem.service;

import com.randika.airportsystem.dto.AirPlaneRequest;
import com.randika.airportsystem.entitie.Airplane;

import java.util.List;

public interface AirplaneService {

    Integer createAirplane(AirPlaneRequest airPlaneRequest);

    Airplane getAirplaneById(Integer id);

    Airplane updateAirplane(Integer id, AirPlaneRequest airPlaneRequest);

    List<Airplane> getAllAirplane();

    List<Airplane> getAllActiveAirplane();

    List<Airplane> getAllDeactivateAirplane();

}
