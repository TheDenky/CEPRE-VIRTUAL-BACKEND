package com.example.ceprevirtualbackend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.ceprevirtualbackend.entity.Estudiante;
import com.example.ceprevirtualbackend.repository.EstudianteRepository;
import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public void run(String... args) throws Exception {
        String id = "00000000";

        Optional<Estudiante> estudianteExistente = estudianteRepository.findByDni(id);
        if (estudianteExistente.isEmpty()) {
            Estudiante estudiante = new Estudiante();
            estudiante.setNombre("Master");
            estudiante.setApellidoPaterno("");
            estudiante.setApellidoMaterno("");
            estudiante.setDni(id);
            estudiante.setCorreo("");
            estudiante.setNumeroCelular("");
            estudiante.setUser("master123");
            estudiante.setPassword("5a21c7b2294de4be2cfa2c49cd6dae16d7a227370931dde195c1e96f5d02694d");
            estudiante.setRole("master");

            estudianteRepository.save(estudiante);
            System.out.println("✅ Estudiante por defecto creado.");
        } else {
            System.out.println("ℹ️ Ya existe en la base de datos.");
        }
    }
}
