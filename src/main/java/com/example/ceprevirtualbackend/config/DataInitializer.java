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
        String idAdmin = "10000000";

        Optional<Estudiante> estudianteExistente = estudianteRepository.findByDni(id);
        Optional<Estudiante> estudianteExistenteAdmin = estudianteRepository.findByDni(idAdmin);

        if (estudianteExistente.isEmpty()) {
            Estudiante estudiante = new Estudiante();
            estudiante.setNombre("Master");
            estudiante.setApellidoPaterno("");
            estudiante.setApellidoMaterno("");
            estudiante.setDni(id);
            estudiante.setCorreo("");
            estudiante.setNumeroCelular("");
            estudiante.setUser("master");
            estudiante.setPassword("$2a$12$ldg9EuAVTVrQTqjvgOXbJe472fiAaJBhaL4TSOA8DZlFRMVwLrnWK");
            estudiante.setRole("master");

            estudianteRepository.save(estudiante);
            System.out.println("✅ Estudiante por defecto creado.");
        } else {
            System.out.println("ℹ️ Estudiante ya existe en la base de datos.");
        }

        if (estudianteExistenteAdmin.isEmpty()) {
            Estudiante estudiante = new Estudiante();
            estudiante.setNombre("Admin");
            estudiante.setApellidoPaterno("");
            estudiante.setApellidoMaterno("");
            estudiante.setDni(idAdmin);
            estudiante.setCorreo("");
            estudiante.setNumeroCelular("");
            estudiante.setUser("admin");
            estudiante.setPassword("$2a$12$nyhDhMrzo3T/pUI5danjP.xkHnryjxQMxuvykhuGFngQ3ySiJPXLO");
            estudiante.setRole("admin");

            estudianteRepository.save(estudiante);
            System.out.println("✅ Admin por defecto creado.");
        } else {
            System.out.println("ℹ️ Admin ya existe en la base de datos.");
        }
    }
}
