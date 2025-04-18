package com.example.ceprevirtualbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToOne
    @JoinColumn(name = "estudianteCicloId")
    @JsonIgnoreProperties(value={"estudianteCiclo", "estudiante", "ciclo", "observacion", "asistencia", "nota"})
    private EstudianteCiclo estudianteCiclo;

    @Column(name = "nombreProceso")
    private String nombreProceso;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "aciertos")
    private String aciertos;
    @Column(name = "desaciertos")
    private String desaciertos;
    @Column(name = "noRespondidos")
    private String noRespondidos;
    @Column(name = "NotaFinal")
    private String NotaFinal;
}
