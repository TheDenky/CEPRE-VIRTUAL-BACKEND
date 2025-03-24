package com.example.ceprevirtualbackend.repository;

import com.example.ceprevirtualbackend.entity.EstudianteCiclo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteCicloRepository extends JpaRepository<EstudianteCiclo, Long> {
    List<EstudianteCiclo> findByCiclo_CicloId(Long cicloId);
    boolean existsByEstudiante_EstudianteIdAndCiclo_CicloId(Long estudianteId, Long cicloId);
    // ✅ Método para encontrar un EstudianteCiclo por estudianteId y cicloId
    Optional<EstudianteCiclo> findByEstudiante_EstudianteIdAndCiclo_CicloId(Long estudianteId, Long cicloId);

    @Transactional
    @Modifying
    @Query("DELETE FROM EstudianteCiclo ec WHERE ec.ciclo.cicloId = :cicloId")
    void deleteByCicloId(Long cicloId);
}
