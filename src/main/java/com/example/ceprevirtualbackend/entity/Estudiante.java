package com.example.ceprevirtualbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tbl_estudiante")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long estudianteId;

    @OneToMany(mappedBy = "estudiante")
    @JsonIgnoreProperties(value={"estudiante"})
    private List<EstudianteCiclo> estudianteCiclo;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellidoPaterno")
    private String apellidoPaterno;
    @Column(name = "apellidoMaterno")
    private String apellidoMaterno;
    @Column(name = "dni", unique = true)
    private String dni;
    @Column(name = "correo")
    private String correo;
    @Column(name = "numeroCelular")
    private String numeroCelular;
    @Column(name = "user")
    private String user;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;

}
