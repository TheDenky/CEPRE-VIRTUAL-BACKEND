package com.example.ceprevirtualbackend.service;

import com.example.ceprevirtualbackend.entity.Asistencia;
import com.example.ceprevirtualbackend.entity.Observacion;
import com.example.ceprevirtualbackend.repository.AsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaService {
    @Autowired
    AsistenciaRepository asistenciaRepository;

    public List<Asistencia> getAsistencias(){
        return asistenciaRepository.findAll();
    }

    public Optional<Asistencia> getAsistencia(Long id){
        return asistenciaRepository.findById(id);
    }
    public List<Asistencia> getObservacionesByEstudianteCiclo(Long estudianteCicloId) {
        return asistenciaRepository.findByEstudianteCicloId(estudianteCicloId);
    }

    public void saveOrUpdateAsistencia(Asistencia asistencia){
        asistenciaRepository.save(asistencia);
    }

    public void deleteAsistencia(Long id){
        asistenciaRepository.deleteById(id);
    }

}
