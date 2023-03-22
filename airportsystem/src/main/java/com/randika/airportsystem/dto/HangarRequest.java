package com.randika.airportsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HangarRequest {

    private String location;
    private boolean status;
    private int airplaneId;
}
