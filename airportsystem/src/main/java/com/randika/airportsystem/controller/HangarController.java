package com.randika.airportsystem.controller;

import com.randika.airportsystem.dto.HangarRequest;
import com.randika.airportsystem.entitie.Hangar;
import com.randika.airportsystem.service.HangarServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/hangar")
public class HangarController {

    @Autowired
    private final HangarServiceImpl hangarService;

    @PostMapping
    public ResponseEntity<Integer> createHangar(@RequestBody HangarRequest hangarRequest){
        Integer hangarId = hangarService.createHangar(hangarRequest);
        return new ResponseEntity<>(hangarId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hangar> getHangarById(@PathVariable Integer id){
        Hangar hangar = hangarService.getHangarById(id);

        return new ResponseEntity<>(hangar,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Hangar>> getAllHangars(){
        List<Hangar> allHangars = hangarService.getAllHangars();
        return new ResponseEntity<>(allHangars,HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Hangar>> getAllActiveHangars(){
        List<Hangar> allActiveHangars = hangarService.getAllActiveHangars();
        return new ResponseEntity<>(allActiveHangars,HttpStatus.OK);
    }

    @GetMapping("/deactivate")
    public ResponseEntity<List<Hangar>> getAllDeactivateHangars(){
        List<Hangar> allDeactivateHangars = hangarService.getAllDeactivateHangars();
        return new ResponseEntity<>(allDeactivateHangars,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hangar> updateHangar(@PathVariable Integer id, @RequestBody HangarRequest hangarRequest){
        Hangar updareHangar = hangarService.updareHangar(id, hangarRequest);
        return new ResponseEntity<>(updareHangar,HttpStatus.OK);
    }






}
