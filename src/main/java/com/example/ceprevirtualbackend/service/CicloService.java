package com.example.ceprevirtualbackend.service;

import com.example.ceprevirtualbackend.entity.Ciclo;
import com.example.ceprevirtualbackend.repository.CicloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CicloService {
    @Autowired
    CicloRepository cicloRepository;

    public List<Ciclo> getCiclos(){
        return cicloRepository.findAll();
    }
    public Optional<Ciclo> getCiclo(Long id){
        return cicloRepository.findById(id);
    }
    public void saveOrUpdateCiclo(Ciclo ciclo){
        cicloRepository.save(ciclo);
    }
    public void deleteCiclo(Long id){
        cicloRepository.deleteById(id);
    }
}
