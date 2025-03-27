package com.example.ceprevirtualbackend.service;

import com.example.ceprevirtualbackend.entity.Estudiante;
import com.example.ceprevirtualbackend.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EstudianteService {

    @Autowired
    private PasswordEncoder passwordEncoder; // 🔥 Inyectar el PasswordEncoder

    @Autowired
    EstudianteRepository estudianteRepository;
    //public boolean verificarCredenciales(String dni, String contrasena, String role){
    //    Estudiante estudiante = estudianteRepository.findByDni(dni);
   //     return estudiante != null && estudiante.getDni().equals(contrasena) && estudiante.getRole().equals(role);
    //}
    public boolean verificarCredenciales(String dni, String contrasena, String role) {
        Optional<Estudiante> estudianteOpt = estudianteRepository.findByDni(dni);

        if (estudianteOpt.isPresent()) {
            Estudiante estudiante = estudianteOpt.get();
            if (estudiante.getRole().equals("master")){
                //return estudiante.getPassword().equals(contrasena);
                return passwordEncoder.matches(contrasena, estudiante.getPassword());
            }else if (estudiante.getRole().equals("admin")) {
                return passwordEncoder.matches(contrasena, estudiante.getPassword()) && estudiante.getRole().equals(role);
                //return estudiante.getPassword().equals(contrasena) && estudiante.getRole().equals(role);
            }else {
                return estudiante.getPassword().equals(contrasena) && estudiante.getRole().equals(role);
            }
        }
        return false; // Retorna `false` si el estudiante no existe
    }
    //public Estudiante
    //public Estudiante getEstudianteByDni(String dni){
    //    return estudianteRepository.findByDni(dni);
    //}
    public Estudiante getEstudianteByDni(String dni) {
        return estudianteRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Estudiante con DNI " + dni + " no encontrado"));
    }

    public List<Estudiante> getEstudiantes(){
        return estudianteRepository.findAll();
    }

    public Optional<Estudiante> getEstudiante(Long id){
        return estudianteRepository.findById(id);
    }

    public void saveOrUpdateEstudiante(Estudiante estudiante){
        if (estudiante.getRole().equalsIgnoreCase("admin") || estudiante.getRole().equalsIgnoreCase("master")) {
            estudiante.setPassword(passwordEncoder.encode(estudiante.getPassword())); // 🔥 Solo cifrar si es ADMIN o MASTER
        }
        estudianteRepository.save(estudiante);
        //estudianteRepository.save(estudiante);
    }

    public void deleteEstudiante(Long id){
        estudianteRepository.deleteById(id);
    }

    //public void guardarEstudiantes(List<Estudiante> estudiantes) {
    //    estudianteRepository.saveAll(estudiantes);
    //}

    //public List<String> guardarEstudiantes(List<Estudiante> estudiantes) {
    //    List<String> errores = new ArrayList<>();
    //    for (Estudiante estudiante : estudiantes) {
    //        if (estudianteRepository.existsByDni(estudiante.getDni())) {
    //            errores.add("DNI duplicado: " + estudiante.getDni());
    //        } else {
    //            estudianteRepository.save(estudiante);
    //        }
    //    }
    //    return errores;
    //}

    public Map<String, Object> guardarEstudiantes(List<Estudiante> estudiantes) {
        Map<String, Object> respuesta = new HashMap<>();
        List<String> errores = new ArrayList<>();
        List<Estudiante> estudiantesGuardados = new ArrayList<>();

        try {
            for (Estudiante estudiante : estudiantes) {
                System.out.println("***** Estudiante Entrando:"+ estudiantes);
                if (estudiante.getDni() == null || estudiante.getDni().isEmpty()) {
                    errores.add("DNI vacío para estudiante: " + estudiante.getNombre());
                    continue;
                }

                if (estudianteRepository.existsByDni(estudiante.getDni())) {
                    errores.add("DNI duplicado: " + estudiante.getDni());
                } else {
                    estudiantesGuardados.add(estudiante);
                }
            }

            // Solo guarda los estudiantes sin errores
            if (!estudiantesGuardados.isEmpty()) {
                estudianteRepository.saveAll(estudiantesGuardados);
            }

            respuesta.put("exitos", estudiantesGuardados.size());
            respuesta.put("errores", errores);
        } catch (Exception e) {
            respuesta.put("error", "Error al guardar estudiantes: " + e.getMessage());
        }

        return respuesta;
    }
}
