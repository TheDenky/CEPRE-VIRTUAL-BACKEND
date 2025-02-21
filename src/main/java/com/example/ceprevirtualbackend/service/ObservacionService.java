package com.example.ceprevirtualbackend.service;

import com.example.ceprevirtualbackend.entity.Observacion;
import com.example.ceprevirtualbackend.repository.ObservacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void saveOrUpdateObservacion(Observacion observacion){
        observacionRepository.save(observacion);
    }
    public void deleteObservacion(Long id){
        observacionRepository.deleteById(id);
    }
}
