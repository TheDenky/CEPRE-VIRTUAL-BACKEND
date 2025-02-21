package com.example.ceprevirtualbackend.service;

import com.example.ceprevirtualbackend.entity.EstudianteCiclo;
import com.example.ceprevirtualbackend.repository.EstudianteCicloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteCicloService {
    @Autowired
    EstudianteCicloRepository estudianteCicloRepository;

    public List<EstudianteCiclo> getEstudiantesCiclos(){
        return estudianteCicloRepository.findAll();
    }
    public Optional<EstudianteCiclo> getEstudianteCiclo(Long id){
        return estudianteCicloRepository.findById(id);
    }
    public void saveOrUdateEstudianteCiclo(EstudianteCiclo estudianteCiclo){
        estudianteCicloRepository.save(estudianteCiclo);
    }
    public void deleteEstudianteCiclo(Long id){
        estudianteCicloRepository.deleteById(id);
    }
}
