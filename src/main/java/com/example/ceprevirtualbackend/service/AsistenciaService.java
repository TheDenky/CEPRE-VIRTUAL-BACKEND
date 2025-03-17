package com.example.ceprevirtualbackend.service;

import com.example.ceprevirtualbackend.entity.Asistencia;
import com.example.ceprevirtualbackend.entity.Observacion;
import com.example.ceprevirtualbackend.repository.AsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    // 📌 Método para guardar múltiples asistencias
    public Map<String, Object> guardarAsistencias(List<Asistencia> asistencias){
        Map<String, Object> respuesta = new HashMap<>();
        List<String> errores = new ArrayList<>();
        int exitos = 0;

        for (Asistencia asistencia : asistencias){
            try {
                asistenciaRepository.save(asistencia);
                exitos++;
            } catch (Exception e){
                errores.add("Error al guardar la asistencia para el estudianteCicloId: " + asistencia.getEstudianteCiclo().getEstudianteCicloId());
            }
        }
        respuesta.put("exitos", exitos);
        respuesta.put("errores", errores);
        return respuesta;
    }

}
