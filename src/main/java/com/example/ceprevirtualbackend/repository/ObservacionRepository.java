package com.example.ceprevirtualbackend.repository;

import com.example.ceprevirtualbackend.entity.Observacion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ObservacionRepository extends JpaRepository<Observacion, Long> {

    @Query("SELECT o FROM Observacion o WHERE o.estudianteCiclo.estudianteCicloId = :estudianteCicloId")
    List<Observacion> findByEstudianteCicloId(@Param("estudianteCicloId") Long estudianteCicloId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Observacion o WHERE o.estudianteCiclo.estudianteCicloId = :estudianteCicloId")
    void deleteByEstudianteCiclo_EstudianteCicloId(@Param("estudianteCicloId") Long estudianteCicloId);

}

