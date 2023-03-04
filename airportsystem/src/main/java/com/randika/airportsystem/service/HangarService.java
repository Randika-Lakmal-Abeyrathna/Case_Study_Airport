package com.randika.airportsystem.service;

import com.randika.airportsystem.dto.HangarRequest;
import com.randika.airportsystem.entitie.Hangar;

import java.util.List;

public interface HangarService {

    Integer createHangar(HangarRequest hangarRequest);

    Hangar getHangarById(Integer id);

    Hangar updareHangar(Integer id, HangarRequest hangarRequest);

    List<Hangar> getAllHangars();

    List<Hangar> getAllActiveHangars();

    List<Hangar> getAllDeactivateHangars();


}
