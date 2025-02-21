package com.example.ceprevirtualbackend.repository;

import com.example.ceprevirtualbackend.entity.EstudianteCiclo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteCicloRepository extends JpaRepository<EstudianteCiclo, Long> {

}
