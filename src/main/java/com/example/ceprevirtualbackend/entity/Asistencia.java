package com.example.ceprevirtualbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tbl_asistencia")
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long asistenciaId;

    //@ManyToOne
    //@JoinColumn(name = "estudianteCicloId")
    //private EstudianteCiclo estudianteCiclo;

    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "curso")
    private String curso;
}
