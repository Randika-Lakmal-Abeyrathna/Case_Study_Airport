package com.randika.airportsystem.service;

import com.randika.airportsystem.dto.HangarRequest;
import com.randika.airportsystem.entitie.Airplane;
import com.randika.airportsystem.entitie.Hangar;
import com.randika.airportsystem.exception.ResourceNotFound;
import com.randika.airportsystem.repository.HangarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Transactional
@Service
public class HangarServiceImpl implements HangarService {

    private final AirplaneServiceImpl airplaneService;
    private final HangarRepository hangarRepository;

    @Override
    public Integer createHangar(HangarRequest hangarRequest) {

        Hangar hangar = new Hangar();
        hangar.setStatus(hangarRequest.isStatus());
        hangar.setLocation(hangarRequest.getLocation());

        Airplane airplane = airplaneService.getAirplaneById(hangarRequest.getAirplaneId());
        hangar.setAirplane(airplane);

        Hangar hangarSave = hangarRepository.save(hangar);

        return hangarSave.getId();
    }

    @Override
    public Hangar getHangarById(Integer id) {
        return hangarRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Hangar not found with id: "+id));
    }

    @Override
    public Hangar updareHangar(Integer id, HangarRequest hangarRequest) {
        Hangar updateHangar = getHangarById(id);
        updateHangar.setLocation(hangarRequest.getLocation());
        updateHangar.setStatus(hangarRequest.isStatus());

        Airplane airplane = airplaneService.getAirplaneById(hangarRequest.getAirplaneId());
        updateHangar.setAirplane(airplane);
        return hangarRepository.save(updateHangar);
    }

    @Override
    public List<Hangar> getAllHangars() {
        return hangarRepository.findAll();
    }

    @Override
    public List<Hangar> getAllActiveHangars() {
        return hangarRepository.findByStatus(true);
    }

    @Override
    public List<Hangar> getAllDeactivateHangars() {
        return hangarRepository.findByStatus(false);
    }
}
