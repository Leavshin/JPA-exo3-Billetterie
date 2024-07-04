package org.example.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event")
    private int id;
    private String nom;
    private LocalDate date;
    private LocalTime heure;
    private int capacite;

    @Embedded
    private Adresse adresse;

    @OneToMany( mappedBy = "event")
    private List<Billet> billets;


}