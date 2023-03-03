package com.randika.airportsystem.service;

import com.randika.airportsystem.dto.AirPlaneRequest;
import com.randika.airportsystem.entitie.Airplane;
import com.randika.airportsystem.entitie.Planetype;
import com.randika.airportsystem.exception.ResourceNotFound;
import com.randika.airportsystem.repository.AirplaneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class AirplaneServiceImpl implements AirplaneService {


    private final AirplaneRepository airplaneRepository;
    private final PlaneTypeServiceImpl planeTypeService;



    @Override
    public Integer createAirplane(AirPlaneRequest airPlaneRequest) {

        Airplane airplane= new Airplane();
        airplane.setCode(airPlaneRequest.getCode());
        airplane.setStatus(airPlaneRequest.isStatus());

        Planetype planetype = planeTypeService.getPlaneTypeByID(airPlaneRequest.getPlanetypeId());

        airplane.setPlanetype(planetype);

        Airplane airplaneSave = airplaneRepository.save(airplane);

        return airplaneSave.getId();
    }

    @Override
    public Airplane getAirplaneById(Integer id) {
        return airplaneRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Airplane not found with id: " +id));
    }

    @Override
    public Airplane updateAirplane(Integer id, AirPlaneRequest airPlaneRequest) {
        Airplane updateAirplane = getAirplaneById(id);
        updateAirplane.setCode(airPlaneRequest.getCode());
        updateAirplane.setStatus(airPlaneRequest.isStatus());

        Planetype planetype = planeTypeService.getPlaneTypeByID(airPlaneRequest.getPlanetypeId());
        updateAirplane.setPlanetype(planetype);

        return airplaneRepository.save(updateAirplane);
    }

    @Override
    public List<Airplane> getAllAirplane() {
        return airplaneRepository.findAll();
    }

    @Override
    public List<Airplane> getAllActiveAirplane() {
        return airplaneRepository.findByStatus(true);
    }

    @Override
    public List<Airplane> getAllDeactivateAirplane() {
        return airplaneRepository.findByStatus(false);
    }
}
