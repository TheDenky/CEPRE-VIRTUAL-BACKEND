package com.example.ceprevirtualbackend.service;

import com.example.ceprevirtualbackend.entity.Estudiante;
import com.example.ceprevirtualbackend.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {
    @Autowired
    EstudianteRepository estudianteRepository;

    public List<Estudiante> getEstudiantes(){
        return estudianteRepository.findAll();
    }

    public Optional<Estudiante> getEstudiante(Long id){
        return estudianteRepository.findById(id);
    }

    public void saveOrUpdateEstudiante(Estudiante estudiante){
        estudianteRepository.save(estudiante);
    }

    public void deleteEstudiante(Long id){
        estudianteRepository.deleteById(id);
    }
}
