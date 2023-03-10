package com.randika.airportsystem.service;

import com.randika.airportsystem.entitie.Planetype;
import com.randika.airportsystem.exception.ResourceNotFound;
import com.randika.airportsystem.repository.PlanetypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
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
        return planetypeRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Plane Type not found with id: "+id));
    }

    @Override
    public Planetype updatePlanetype(Integer id, Planetype planetype) {
        Planetype updatePlane = getPlaneTypeByID(id);
        updatePlane.setCapacity(planetype.getCapacity());
        updatePlane.setModel(planetype.getModel());
        updatePlane.setWeight(planetype.getWeight());
        updatePlane.setStatus(planetype.isStatus());

        return planetypeRepository.save(updatePlane);
    }

    @Override
    public List<Planetype> getAllPlaneType() {
        return  planetypeRepository.findAll();
    }

    @Override
    public List<Planetype> getAllActivePlaneType() {
        return planetypeRepository.findByStatus(true);
    }

    @Override
    public List<Planetype> getAllDeactivatePlaneType() {
        return planetypeRepository.findByStatus(false);
    }


}
