package com.example.ceprevirtualbackend.service;

import com.example.ceprevirtualbackend.entity.Observacion;
import com.example.ceprevirtualbackend.repository.ObservacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ObservacionService {
    @Autowired
    ObservacionRepository observacionRepository;

    public List<Observacion> getObservaciones(){
        return observacionRepository.findAll();
    }
    public Optional<Observacion> getObservacion(Long id){
        return observacionRepository.findById(id);
    }
    public List<Observacion> getObservacionesByEstudianteCiclo(Long estudianteCicloId) {
        return observacionRepository.findByEstudianteCicloId(estudianteCicloId);
    }
    public void saveOrUpdateObservacion(Observacion observacion){
        observacionRepository.save(observacion);
    }
    public void deleteObservacion(Long id){
        observacionRepository.deleteById(id);
    }
    public Map<String, Object> guardarObservaciones(List<Observacion> observaciones){
        Map<String, Object> respuesta = new HashMap<>();
        List<String> errores = new ArrayList<>();
        int exitos = 0;

        for (Observacion observacion : observaciones){
            try {
                observacionRepository.save(observacion);
                exitos++;
            } catch (Exception e) {
                errores.add("Error al guardar la observacion para el estudianteCicloId: "+ observacion.getEstudianteCiclo().getEstudianteCicloId());
            }
        }

        respuesta.put("Exitos", exitos);
        respuesta.put("Errores", errores);
        return respuesta;
    }
}
