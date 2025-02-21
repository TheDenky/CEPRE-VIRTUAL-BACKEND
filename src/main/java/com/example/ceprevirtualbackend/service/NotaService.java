package com.example.ceprevirtualbackend.service;

import com.example.ceprevirtualbackend.entity.Nota;
import com.example.ceprevirtualbackend.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {
    @Autowired
    NotaRepository notaRepository;
    public List<Nota> getNotas(){
        return notaRepository.findAll();
    }
    public Optional<Nota> getNota(Long id){
        return notaRepository.findById(id);
    }
    public void saveOrUpdateNota(Nota nota){
        notaRepository.save(nota);
    }
    public void deleteNota(Long id){
        notaRepository.deleteById(id);
    }

}
