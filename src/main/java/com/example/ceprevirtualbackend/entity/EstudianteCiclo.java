package com.example.ceprevirtualbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @OneToMany(mappedBy = "estudianteCiclo")
    @JsonIgnoreProperties(value="estudianteCiclo")
    private List<Asistencia> asistencia;

    @OneToMany(mappedBy = "estudianteCiclo")
    @JsonIgnoreProperties(value="estudianteCiclo")
    private List<Nota> nota;

    @OneToMany(mappedBy = "estudianteCiclo")
    @JsonIgnoreProperties(value="estudianteCiclo")
    private List<Observacion> observacion;

    @ManyToOne
    @JoinColumn(name = "estudianteId")
    //@JsonIgnore
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "cicloId")
    //@JsonIgnore
    private Ciclo ciclo;
}
