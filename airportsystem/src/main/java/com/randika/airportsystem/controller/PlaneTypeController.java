package com.randika.airportsystem.controller;

import com.randika.airportsystem.entitie.Planetype;
import com.randika.airportsystem.service.PlaneTypeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/planetype")
@AllArgsConstructor
public class PlaneTypeController {

    @Autowired
    private final PlaneTypeServiceImpl planeTypeService;

    @PostMapping
    public ResponseEntity<Integer> createPlaneType(@RequestBody Planetype planetype){
        Integer planeTypeId = planeTypeService.createPlaneType(planetype);
        return new ResponseEntity<>(planeTypeId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planetype> getPlaneTypeById(@PathVariable Integer id){
        Planetype planetype = planeTypeService.getPlaneTypeByID(id);
        return new ResponseEntity<>(planetype,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Planetype>> getAllPlaneType(){
        List<Planetype> allPlaneType = planeTypeService.getAllPlaneType();
        return new ResponseEntity<>(allPlaneType,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Planetype> updatePlaneType(@PathVariable Integer id, @RequestBody Planetype planetype){
        Planetype updatePlanetype = planeTypeService.updatePlanetype(id, planetype);
        return new ResponseEntity<>(updatePlanetype,HttpStatus.OK);
    }



}
