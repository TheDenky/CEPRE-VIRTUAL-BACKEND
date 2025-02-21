package com.example.ceprevirtualbackend.controller;

import com.example.ceprevirtualbackend.entity.Estudiante;
import com.example.ceprevirtualbackend.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/estudiante")
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public List<Estudiante> getAll(){
        return estudianteService.getEstudiantes();
    }
    @GetMapping("/{estudianteId}")
    public Optional<Estudiante> getById(@PathVariable("estudianteId") Long estudianteId){
        return estudianteService.getEstudiante(estudianteId);
    }
    @PostMapping
    public Estudiante saveUpdate(@RequestBody Estudiante estudiante){
        estudianteService.saveOrUpdateEstudiante(estudiante);
        return estudiante;
    }
    @DeleteMapping("/{estudianteId}")
    public void delete(@PathVariable("estudianteId") Long estudianteId){
        estudianteService.deleteEstudiante(estudianteId);
    }
}
