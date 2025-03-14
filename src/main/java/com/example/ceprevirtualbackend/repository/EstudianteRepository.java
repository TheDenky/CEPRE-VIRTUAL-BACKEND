package com.example.ceprevirtualbackend.repository;

import com.example.ceprevirtualbackend.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    // Asegurar que el método devuelve un Optional
    Optional<Estudiante> findByDni(String dni);
    //Optional<Estudiante> findStudentByDni(String dni);
    boolean existsByDni(String dni);


}
