package com.example.ceprevirtualbackend.repository;

import com.example.ceprevirtualbackend.entity.Nota;
import com.example.ceprevirtualbackend.entity.Observacion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
    @Query("SELECT o FROM Nota o WHERE o.estudianteCiclo.estudianteCicloId = :estudianteCicloId")
    List<Nota> findByEstudianteCicloId(@Param("estudianteCicloId") Long estudianteCicloId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Nota o WHERE o.estudianteCiclo.estudianteCicloId = :estudianteCicloId")
    void deleteByEstudianteCiclo_EstudianteCicloId(@Param("estudianteCicloId") Long estudianteCicloId);
}
