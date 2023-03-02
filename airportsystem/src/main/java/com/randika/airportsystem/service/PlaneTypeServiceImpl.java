package com.randika.airportsystem.service;

import com.randika.airportsystem.entitie.Planetype;
import com.randika.airportsystem.exception.ResourceNotFound;
import com.randika.airportsystem.repository.PlanetypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlaneTypeServiceImpl implements PlaneTypeService {

    @Autowired
    private final PlanetypeRepository planetypeRepository;

    @Override
    public Integer createPlaneType(Planetype planetype) {
        Planetype planetypeSave = planetypeRepository.save(planetype);
        return planetypeSave.getId();
    }

    @Override
    public Planetype getPlaneTypeByID(Integer id) {
        return planetypeRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Plane Type Not Found"));
    }

    @Override
    public Planetype updatePlanetype(Integer id, Planetype planetype) {
        Planetype updatePlane = getPlaneTypeByID(id);
        updatePlane.setCapacity(planetype.getCapacity());
        updatePlane.setModel(planetype.getModel());
        updatePlane.setWeight(planetype.getWeight());

        return planetypeRepository.save(updatePlane);
    }

    @Override
    public List<Planetype> getAllPlaneType() {
        return  planetypeRepository.findAll();
    }


}
