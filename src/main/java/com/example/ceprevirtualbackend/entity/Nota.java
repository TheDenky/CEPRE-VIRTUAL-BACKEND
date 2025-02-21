package com.example.ceprevirtualbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tbl_nota")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notaId;

    //@ManyToOne
    //@JoinColumn(name = "estudianteCicloId")
    //private EstudianteCiclo estudianteCiclo;

    @Column(name = "nombreProceso")
    private String nombreProceso;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "asiertos")
    private String asiertos;
    @Column(name = "desaciertos")
    private Integer desaciertos;
    @Column(name = "noRespondidos")
    private Integer noRespondidos;
    @Column(name = "NotaFinal")
    private Double NotaFinal;
}
