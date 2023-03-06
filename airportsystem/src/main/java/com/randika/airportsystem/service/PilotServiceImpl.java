package com.randika.airportsystem.service;

import com.randika.airportsystem.entitie.Pilot;
import com.randika.airportsystem.exception.ResourceNotFound;
import com.randika.airportsystem.repository.PilotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Transactional
@Service
public class PilotServiceImpl implements PilotService {

    private final PilotRepository pilotRepository;

    @Override
    public Integer createPilot(Pilot pilot) {

        Pilot pilotSave = pilotRepository.save(pilot);

        return pilotSave.getId();
    }

    @Override
    public Pilot getPilotById(Integer id) {
        return pilotRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Pilot not found with id: "+id));
    }

    @Override
    public Pilot updatePilot(Integer id, Pilot pilot) {
        Pilot updatePilot = getPilotById(id);
        updatePilot.setFirstname(pilot.getFirstname());
        updatePilot.setLastname(pilot.getLastname());
        updatePilot.setLicence(pilot.getLicence());
        return pilotRepository.save(updatePilot);
    }

    @Override
    public List<Pilot> getAllPilots() {
        return pilotRepository.findAll();
    }
}
