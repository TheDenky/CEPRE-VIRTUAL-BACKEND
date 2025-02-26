package com.example.ceprevirtualbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToOne
    @JoinColumn(name = "estudianteCicloId")
    @JsonIgnoreProperties(value={"estudianteCiclo", "estudiante", "ciclo", "observacion", "asistencia", "nota"})
    private EstudianteCiclo estudianteCiclo;

    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "curso")
    private String curso;
}
