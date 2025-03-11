package com.example.ceprevirtualbackend.controller;

import aj.org.objectweb.asm.Opcodes;
import com.example.ceprevirtualbackend.entity.Observacion;
import com.example.ceprevirtualbackend.service.ObservacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/observacion")
public class ObservacionController {
    @Autowired
    private ObservacionService observacionService;

    @GetMapping
    public List<Observacion> getAll(){
        return observacionService.getObservaciones();
    }
    @GetMapping("/{observacionId}")
    public Optional<Observacion> getById(@PathVariable("observacionId") Long observacionId){
        return observacionService.getObservacion(observacionId);
    }
    @GetMapping("/ec/{estudianteCicloId}")
    public List<Observacion> getByEstudianteCiclo(@PathVariable("estudianteCicloId") Long estudianteCicloId) {
        return observacionService.getObservacionesByEstudianteCiclo(estudianteCicloId);
    }
    @PostMapping
    public Observacion saveUpdate(@RequestBody Observacion observacion){
        observacionService.saveOrUpdateObservacion(observacion);
        return observacion;
    }
    @DeleteMapping("/{observacionId}")
    public void delete(@PathVariable("observacionId") Long observacionId){
        observacionService.deleteObservacion(observacionId);
    }
}
