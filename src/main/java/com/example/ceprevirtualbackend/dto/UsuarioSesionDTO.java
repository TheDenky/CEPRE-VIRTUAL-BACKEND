package com.example.ceprevirtualbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class UsuarioSesionDTO implements Serializable {
    private static final long serialVersionUID = 1L; // Evita problemas de serialización

    private String nombre;
    private String apellidoPaterno;
    private String dni;
    private String correo;
    private String role;
}
