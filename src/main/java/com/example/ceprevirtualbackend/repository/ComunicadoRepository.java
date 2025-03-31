package com.example.ceprevirtualbackend.repository;

import com.example.ceprevirtualbackend.entity.Comunicado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunicadoRepository extends JpaRepository<Comunicado, Long> {
}
