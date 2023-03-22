package com.randika.airportsystem.entitie;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class Hangar {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String location;
    private boolean status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airplane_id",referencedColumnName = "id")
    private Airplane airplane;

    public Hangar(String location, boolean status, Airplane airplane) {
        this.location = location;
        this.status = status;
        this.airplane = airplane;
    }
}
