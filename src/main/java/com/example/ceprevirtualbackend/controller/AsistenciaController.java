package com.example.ceprevirtualbackend.controller;

import com.example.ceprevirtualbackend.entity.Asistencia;
import com.example.ceprevirtualbackend.entity.Observacion;
import com.example.ceprevirtualbackend.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/asistencia")
public class AsistenciaController {
    @Autowired
    private AsistenciaService asistenciaService;

    @GetMapping
    public List<Asistencia> getAll(){
        return asistenciaService.getAsistencias();
    }
    @GetMapping("/{asistenciaId}")
    public Optional<Asistencia> getById(@PathVariable("asistenciaId") Long asistenciaId){
        return asistenciaService.getAsistencia(asistenciaId);
    }
    @GetMapping("/ec/{estudianteCicloId}")
    public List<Asistencia> getByEstudianteCiclo(@PathVariable("estudianteCicloId") Long estudianteCicloId) {
        return asistenciaService.getObservacionesByEstudianteCiclo(estudianteCicloId);
    }
    @PostMapping
    public Asistencia saveUpdate(@RequestBody Asistencia asistencia){
        asistenciaService.saveOrUpdateAsistencia(asistencia);
        return asistencia;
    }
    @DeleteMapping("/{asistenciaId}")
    public void delete(@PathVariable("asistenciaId") Long asistenciaId){
        asistenciaService.deleteAsistencia(asistenciaId);
    }
}
