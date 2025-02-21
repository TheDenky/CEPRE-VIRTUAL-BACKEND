package com.example.ceprevirtualbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_observacion")
public class Observacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long observacionId;

    //@ManyToOne
    //@JoinColumn(name = "estudianteCicloId")
    //private EstudianteCiclo estudianteCiclo;

    @Column(name = "motivo")
    private String motivo;
    @Column(name = "detalle")
    private String detalle;
}
