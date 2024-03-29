package com.randika.airportsystem.entitie;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class Planetype {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @NotBlank
    private String model;
    @NotNull
    private int capacity;
    @NotNull
    private double weight;
    private boolean status;

    public Planetype(String model, int capacity, double weight, boolean status) {
        this.model = model;
        this.capacity = capacity;
        this.weight = weight;
        this.status = status;
    }
}
