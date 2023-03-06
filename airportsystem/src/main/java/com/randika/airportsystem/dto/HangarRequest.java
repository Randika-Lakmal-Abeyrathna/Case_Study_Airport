package com.randika.airportsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HangarRequest {

    private String location;
    private boolean status;
    private int airplaneId;
}
