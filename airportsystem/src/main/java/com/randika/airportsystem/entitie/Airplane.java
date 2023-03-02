package com.randika.airportsystem.entitie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Airplane {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(min = 3, max = 10)
    private String code;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "planetype_id",referencedColumnName = "id")
    private Planetype planetype;

}
