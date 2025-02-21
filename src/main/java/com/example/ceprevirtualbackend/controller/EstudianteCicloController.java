package com.example.ceprevirtualbackend.controller;

import com.example.ceprevirtualbackend.entity.EstudianteCiclo;
import com.example.ceprevirtualbackend.service.EstudianteCicloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/estudianteciclo")
public class EstudianteCicloController {
    @Autowired
    private EstudianteCicloService estudianteCicloService;

    @GetMapping
    public List<EstudianteCiclo> getAll(){
        return estudianteCicloService.getEstudiantesCiclos();
    }
    @GetMapping("/{estudianteCicloId}")
    public Optional<EstudianteCiclo> getById(@PathVariable("estudianteCicloId") Long estudianteCicloId){
        return estudianteCicloService.getEstudianteCiclo(estudianteCicloId);
    }
    @PostMapping
    public EstudianteCiclo saveUpdate(@RequestBody EstudianteCiclo estudianteCiclo){
        estudianteCicloService.saveOrUdateEstudianteCiclo(estudianteCiclo);
        return estudianteCiclo;
    }
    @DeleteMapping("/{estudianteCicloId}")
    public void delete(@PathVariable("estudianteCicloId") Long estudianteCicloId){
        estudianteCicloService.deleteEstudianteCiclo(estudianteCicloId);
    }
}
