package com.example.ceprevirtualbackend.repository;

import com.example.ceprevirtualbackend.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {

}
