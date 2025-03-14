package com.example.ceprevirtualbackend.controller;

import com.example.ceprevirtualbackend.entity.EstudianteCiclo;
import com.example.ceprevirtualbackend.service.EstudianteCicloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
    @GetMapping("/ciclo/{cicloId}")
    public List<EstudianteCiclo> getByCiclo(@PathVariable Long cicloId) {
        return estudianteCicloService.getByCiclo(cicloId);
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

    @PostMapping("/importar")
    public ResponseEntity<Map<String, Object>> importarMatriculas(@RequestBody Map<String, Object> request) {
        Map<String, Object> respuesta = estudianteCicloService.matricularEstudiantes(request);
        return ResponseEntity.ok(respuesta);
    }


}
