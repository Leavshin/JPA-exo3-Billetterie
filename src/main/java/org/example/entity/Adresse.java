package org.example.entity;// Adresse.java
import lombok.*;

import javax.persistence.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Adresse {

    private String rue;
    private String ville;
}
