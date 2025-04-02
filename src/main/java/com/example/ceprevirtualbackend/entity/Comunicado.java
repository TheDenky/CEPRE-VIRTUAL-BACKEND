package com.example.ceprevirtualbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tbl_comunicado")
public class Comunicado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comunicadoId;

    @ManyToOne
    @JoinColumn(name = "cicloId")
    @JsonIgnoreProperties(value = {"ciclo", "material", "comunicado"})
    private Ciclo ciclo;

    @Column(name = "tipo")
    public String tipo;

    @Column(name = "descripcion")
    public String descripcion;

    @Column(name = "fecha")
    public LocalDate fecha;
}
