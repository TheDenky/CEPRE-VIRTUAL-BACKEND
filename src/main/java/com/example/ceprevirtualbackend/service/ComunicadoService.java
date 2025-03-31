package com.example.ceprevirtualbackend.service;

import com.example.ceprevirtualbackend.entity.Comunicado;
import com.example.ceprevirtualbackend.repository.ComunicadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComunicadoService {
    @Autowired
    ComunicadoRepository comunicadoRepository;
    public List<Comunicado> getComunicados(){
        return comunicadoRepository.findAll();
    }
    public Optional<Comunicado> getComunicado(Long id){
        return comunicadoRepository.findById(id);
    }
    public void saveOrUpdateComunicado(Comunicado comunicado){
        comunicadoRepository.save(comunicado);
    }
    public void deleteComunicado(Long id){
        comunicadoRepository.deleteById(id);
    }
}
