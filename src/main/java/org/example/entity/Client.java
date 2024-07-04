package org.example.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long id;

    private String nom;
    private String prenom;
    private int age;
    private String telephone;

    @Embedded
    private Adresse adresse;

    @OneToMany(mappedBy = "client")
    private List<Billet> billets;
}
