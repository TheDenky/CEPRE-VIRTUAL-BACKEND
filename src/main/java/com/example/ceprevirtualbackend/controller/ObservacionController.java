package com.example.ceprevirtualbackend.controller;

import aj.org.objectweb.asm.Opcodes;
import com.example.ceprevirtualbackend.entity.Observacion;
import com.example.ceprevirtualbackend.service.ObservacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
    @DeleteMapping("/deleteByEstudianteCiclo/{estudianteCicloId}")
    public ResponseEntity<?> deleteByEstudianteCiclo(@PathVariable Long estudianteCicloId) {
        try {
            observacionService.deleteByEstudianteCiclo(estudianteCicloId);
            return ResponseEntity.ok("✅ Todas las observaciones han sido eliminadas.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("⚠ Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Error interno al eliminar observaciones.");
        }
    }
    // 📌 Endpoint para registrar observacion en lote
    @PostMapping("/importar")
    public ResponseEntity<Map<String, Object>> importarObservaciones(@RequestBody List<Observacion> observaciones){
        Map<String, Object> respuesta = observacionService.guardarObservaciones(observaciones);
        return ResponseEntity.ok(respuesta);
    }
}
