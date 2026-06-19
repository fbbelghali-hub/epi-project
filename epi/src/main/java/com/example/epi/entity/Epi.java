package com.example.epi.entity;
import com.example.epi.enums.TypeEPI;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "epis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Epi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeEPI typeEPI;

    public Epi(TypeEPI typeEPI) {
        this.typeEPI = typeEPI;
    }
}
