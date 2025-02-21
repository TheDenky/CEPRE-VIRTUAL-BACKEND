package com.example.ceprevirtualbackend.repository;

import com.example.ceprevirtualbackend.entity.Ciclo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CicloRepository extends JpaRepository<Ciclo, Long> {

}
