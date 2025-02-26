package com.example.ceprevirtualbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_observacion")
public class Observacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long observacionId;

    @ManyToOne
    @JoinColumn(name = "estudianteCicloId")
    @JsonIgnoreProperties(value={"estudianteCiclo", "estudiante", "ciclo", "observacion", "asistencia", "nota"})
    private EstudianteCiclo estudianteCiclo;

    @Column(name = "motivo")
    private String motivo;
    @Column(name = "detalle")
    private String detalle;
}
