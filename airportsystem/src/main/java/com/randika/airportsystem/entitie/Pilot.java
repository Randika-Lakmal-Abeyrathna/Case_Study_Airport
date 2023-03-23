package com.randika.airportsystem.entitie;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class Pilot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Pilot Licence cannot be null")
    @NotBlank(message = "Pilot Licence cannot be blank")
    @Size(min = 5, max = 25,message = "Invalid Licence , Licence must be 5-25 characters")
    private String licence;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;

    public Pilot(String licence, String firstname, String lastname) {
        this.licence = licence;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
