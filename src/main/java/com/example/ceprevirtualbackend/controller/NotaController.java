package com.example.ceprevirtualbackend.controller;

import com.example.ceprevirtualbackend.entity.Nota;
import com.example.ceprevirtualbackend.entity.Observacion;
import com.example.ceprevirtualbackend.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/nota")
public class NotaController {
    @Autowired
    private NotaService notaService;

    @GetMapping
    public List<Nota> getAll(){
        return notaService.getNotas();
    }
    @GetMapping("/{notaId}")
    public Optional<Nota> getById(@PathVariable("notaId") Long notaId){
        return notaService.getNota(notaId);
    }
    @GetMapping("/ec/{estudianteCicloId}")
    public List<Nota> getByEstudianteCiclo(@PathVariable("estudianteCicloId") Long estudianteCicloId) {
        return notaService.getObservacionesByEstudianteCiclo(estudianteCicloId);
    }
    @PostMapping
    public Nota saveUpdate(@RequestBody Nota nota){
        notaService.saveOrUpdateNota(nota);
        return nota;
    }
    @DeleteMapping("/{notaId}")
    public void delete(@PathVariable("notaId") Long notaId){
        notaService.deleteNota(notaId);
    }

    // 📌 Endpoint para registrar notas en lote
    @PostMapping("/importar")
    public ResponseEntity<Map<String, Object>> importarNotas(@RequestBody List<Nota> notas) {
        Map<String, Object> respuesta = notaService.guardarNotas(notas);
        return ResponseEntity.ok(respuesta);
    }
}
