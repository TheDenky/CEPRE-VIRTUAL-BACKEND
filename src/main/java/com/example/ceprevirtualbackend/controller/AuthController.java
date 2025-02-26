package com.example.ceprevirtualbackend.controller;

import com.example.ceprevirtualbackend.dto.LoginRequest;
import com.example.ceprevirtualbackend.entity.Estudiante;
import com.example.ceprevirtualbackend.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/auth")
@CrossOrigin(origins = "http://localhost:5173") // Habilitar CORS para el frontend
public class AuthController {
    @Autowired
    private EstudianteService estudianteService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        boolean authentic = estudianteService.verificarCredenciales(loginRequest.getUser(), loginRequest.getPassword());

        if(authentic){
            //return ResponseEntity.ok("Login exitoso");
            Estudiante estudiante = estudianteService.getEstudianteByDni(loginRequest.getUser());
            return ResponseEntity.ok(estudiante);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales Incorrectas");
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Credenciales Incorrectas\"}");
        }
    }
}
