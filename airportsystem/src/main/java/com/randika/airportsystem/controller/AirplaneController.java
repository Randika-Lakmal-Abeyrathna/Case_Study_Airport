package com.randika.airportsystem.controller;

import com.randika.airportsystem.dto.AirPlaneRequest;
import com.randika.airportsystem.entitie.Airplane;
import com.randika.airportsystem.service.AirplaneServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airplane")
@AllArgsConstructor
public class AirplaneController {

    @Autowired
    private final AirplaneServiceImpl airplaneService;

    @PostMapping
    public ResponseEntity<Integer> createAirplane(@RequestBody AirPlaneRequest airPlaneRequest){
        Integer airplaneId = airplaneService.createAirplane(airPlaneRequest);
        return new ResponseEntity<>(airplaneId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Airplane> getAirplaneById(@PathVariable Integer id){
        Airplane airplane = airplaneService.getAirplaneById(id);
        return new ResponseEntity<>(airplane,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Airplane>> getAllAirplane(){
        List<Airplane> allAirplane = airplaneService.getAllAirplane();
        return new ResponseEntity<>(allAirplane,HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Airplane>> getAllActiveAirplane(){
        List<Airplane> allActiveAirplane = airplaneService.getAllActiveAirplane();
        return new ResponseEntity<>(allActiveAirplane,HttpStatus.OK);
    }

    @GetMapping("/deactivate")
    public ResponseEntity<List<Airplane>> getAllDeactivateAirplane(){
        List<Airplane> allDeactivateAirplane = airplaneService.getAllDeactivateAirplane();
        return new ResponseEntity<>(allDeactivateAirplane,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airplane> updateAirplane(@PathVariable Integer id, @RequestBody AirPlaneRequest airPlaneRequest){
        Airplane updateAirplane = airplaneService.updateAirplane(id, airPlaneRequest);
        return new ResponseEntity<>(updateAirplane,HttpStatus.OK);
    }


}
