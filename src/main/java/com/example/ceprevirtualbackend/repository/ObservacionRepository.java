package com.example.ceprevirtualbackend.repository;

import com.example.ceprevirtualbackend.entity.Observacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObservacionRepository extends JpaRepository<Observacion, Long> {
}
