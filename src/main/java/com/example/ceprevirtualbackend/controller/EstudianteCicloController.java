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

    // 📌 Nuevo endpoint para obtener estudianteCicloId por DNI y cicloId
    @PostMapping("/getByDniAndCiclo")
    public ResponseEntity<List<Map<String, Object>>> getByDniAndCiclo(@RequestBody Map<String, Object> request) {
        Long cicloId = Long.parseLong(request.get("cicloId").toString());
        List<String> dniList = (List<String>) request.get("dniList");

        List<Map<String, Object>> resultado = estudianteCicloService.getEstudianteCicloByDniAndCiclo(cicloId, dniList);
        System.out.println("🔍 Datos a enviar: " + resultado); // Agregar log para verificar en backend

        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/eliminarPorCiclo/{cicloId}")
    public ResponseEntity<String> eliminarMatriculasPorCiclo(@PathVariable Long cicloId) {
        estudianteCicloService.eliminarMatriculasPorCiclo(cicloId);
        return ResponseEntity.ok("✅ Matrículas eliminadas con éxito.");
    }

}
