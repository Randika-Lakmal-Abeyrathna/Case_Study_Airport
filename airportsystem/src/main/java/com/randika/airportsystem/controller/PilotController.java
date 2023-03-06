package com.randika.airportsystem.controller;


import com.randika.airportsystem.entitie.Pilot;
import com.randika.airportsystem.service.PilotServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/pilot")
public class PilotController {

    @Autowired
    private final PilotServiceImpl pilotService;

    @PostMapping
    public ResponseEntity<Integer> createPilot(@RequestBody Pilot pilot){
        Integer pilotId = pilotService.createPilot(pilot);
        return new ResponseEntity<>(pilotId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pilot> getPilotById(@PathVariable Integer id){
        Pilot pilot = pilotService.getPilotById(id);
        return new ResponseEntity<>(pilot,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pilot>> getAllPilots(){
        List<Pilot> allPilots = pilotService.getAllPilots();
        return new ResponseEntity<>(allPilots,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pilot> updatePilot(@PathVariable Integer id, @RequestBody Pilot pilot){
        Pilot updatePilot = pilotService.updatePilot(id, pilot);
        return new ResponseEntity<>(updatePilot,HttpStatus.OK);
    }


}
