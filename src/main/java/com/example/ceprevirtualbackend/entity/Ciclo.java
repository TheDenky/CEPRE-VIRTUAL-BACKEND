package com.example.ceprevirtualbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_ciclo")
public class Ciclo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cicloId;

    @OneToMany(mappedBy = "ciclo")
    private List<EstudianteCiclo> estudianteCiclo;

    @Column(name = "nombreCiclo")
    private String nombreCiclo;
    @Column(name = "numeroCiclo")
    private String numeroCiclo;
    @Column(name = "fechaInicio")
    private LocalDate fechaInicio;
    @Column(name = "fechaFin")
    private LocalDate fechaFin;
}
