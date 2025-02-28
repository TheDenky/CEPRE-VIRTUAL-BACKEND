package com.example.ceprevirtualbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materialId;

    @ManyToOne
    @JoinColumn(name = "cicloId")
    @JsonIgnoreProperties(value={"ciclo","material"})
    private Ciclo ciclo;

    @Column(name = "tipo")
    public String tipo;

    @Column(name = "link")
    public String link;
}
