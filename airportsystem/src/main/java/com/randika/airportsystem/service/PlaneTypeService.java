package com.randika.airportsystem.service;

import com.randika.airportsystem.entitie.Planetype;

import java.util.List;

public interface PlaneTypeService {

    Integer createPlaneType(Planetype planetype);
    Planetype getPlaneTypeByID(Integer id);

    Planetype updatePlanetype(Integer id, Planetype planetype);

    List<Planetype> getAllPlaneType();

    List<Planetype> getAllActivePlaneType();

    List<Planetype> getAllDeactivatePlaneType();

}
