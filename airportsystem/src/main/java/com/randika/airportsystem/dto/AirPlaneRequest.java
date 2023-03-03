package com.randika.airportsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AirPlaneRequest {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 10)
    private String code;
    private boolean status;
    private int planetypeId;

}
