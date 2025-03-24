package com.example.ceprevirtualbackend.service;

import com.example.ceprevirtualbackend.entity.Nota;
import com.example.ceprevirtualbackend.repository.NotaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public List<Nota> getObservacionesByEstudianteCiclo(Long estudianteCicloId) {
        return notaRepository.findByEstudianteCicloId(estudianteCicloId);
    }
    public void saveOrUpdateNota(Nota nota){
        notaRepository.save(nota);
    }
    public void deleteNota(Long id){
        notaRepository.deleteById(id);
    }

    @Transactional
    public void deleteByEstudianteCiclo(Long estudianteCicloId) {
        notaRepository.deleteByEstudianteCiclo_EstudianteCicloId(estudianteCicloId);
    }

    // 📌 Método para guardar múltiples notas
    public Map<String, Object> guardarNotas(List<Nota> notas) {
        Map<String, Object> respuesta = new HashMap<>();
        List<String> errores = new ArrayList<>();
        int exitos = 0;

        for (Nota nota : notas) {
            try {
                notaRepository.save(nota);
                exitos++;
            } catch (Exception e) {
                errores.add("Error al guardar la nota para estudianteCicloId: " +
                        nota.getEstudianteCiclo().getEstudianteCicloId());
            }
        }

        respuesta.put("exitos", exitos);
        respuesta.put("errores", errores);
        return respuesta;
    }

}
