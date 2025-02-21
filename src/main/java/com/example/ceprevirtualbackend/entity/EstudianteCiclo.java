package com.example.ceprevirtualbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tbl_estudiante_ciclo")
public class EstudianteCiclo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long estudianteCicloId;

    //@OneToMany(mappedBy = "estudianteCiclo")
    //private List<Asistencia> asistencia;

    //@OneToMany(mappedBy = "estudianteCiclo")
    //private Nota nota;

    //@OneToMany(mappedBy = "estudianteCiclo")
    //private Observacion observacion;

    @ManyToOne
    @JoinColumn(name = "estudianteId")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "cicloId")
    private Ciclo ciclo;
}
