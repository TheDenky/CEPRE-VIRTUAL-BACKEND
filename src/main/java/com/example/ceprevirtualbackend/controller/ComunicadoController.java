package com.example.ceprevirtualbackend.controller;

import com.example.ceprevirtualbackend.entity.Comunicado;
import com.example.ceprevirtualbackend.service.ComunicadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/comunicado")
public class ComunicadoController {
    @Autowired
    private ComunicadoService comunicadoService;

    @GetMapping
    public List<Comunicado> getAll(){
        return comunicadoService.getComunicados();
    }
    @GetMapping("/{comunicadoId}")
    public Optional<Comunicado> getById(@PathVariable("comunicadoId") Long comunicadoId){
        return comunicadoService.getComunicado(comunicadoId);
    }
    @PostMapping
    public Comunicado saveUpdate(@RequestBody Comunicado comunicado){
        comunicadoService.saveOrUpdateComunicado(comunicado);
        return comunicado;
    }
    @DeleteMapping("/{comunicadoId}")
    public void delete(@PathVariable("comunicadoId") Long comunicadoId){
        comunicadoService.deleteComunicado(comunicadoId);
    }
}
