package com.randika.airportsystem.entitie;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity(name = "pilot_timetable")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class PilotTimetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private Instant date;
    private boolean status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aireplane_id",referencedColumnName = "id")
    private Airplane airplane;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pilot_id",referencedColumnName = "id")
    private Pilot pilot;

}
