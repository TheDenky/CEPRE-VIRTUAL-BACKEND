package com.example.ceprevirtualbackend.controller;

import com.example.ceprevirtualbackend.entity.Ciclo;
import com.example.ceprevirtualbackend.service.CicloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/ciclo")
public class CicloController {
    @Autowired
    private CicloService cicloService;

    @GetMapping
    public List<Ciclo> getAll(){
        return cicloService.getCiclos();
    }
    @GetMapping("/{cicloId}")
    public Optional<Ciclo> getById(@PathVariable("cicloId") Long cicloId){
        return cicloService.getCiclo(cicloId);
    }
    @PostMapping
    public Ciclo saveUpdate(@RequestBody Ciclo ciclo){
        cicloService.saveOrUpdateCiclo(ciclo);
        return ciclo;
    }
    @DeleteMapping("/{cicloId}")
    public void delete(@PathVariable("cicloId") Long cicloId){
        cicloService.deleteCiclo(cicloId);
    }
}
