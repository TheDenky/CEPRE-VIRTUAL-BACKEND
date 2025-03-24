package com.example.ceprevirtualbackend.controller;

import com.example.ceprevirtualbackend.entity.Asistencia;
import com.example.ceprevirtualbackend.entity.Observacion;
import com.example.ceprevirtualbackend.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
    @DeleteMapping("/deleteByEstudianteCiclo/{estudianteCicloId}")
    public ResponseEntity<?> deleteByEstudianteCiclo(@PathVariable Long estudianteCicloId) {
        try {
            asistenciaService.deleteByEstudianteCiclo(estudianteCicloId);
            return ResponseEntity.ok("✅ Todas las asistencias han sido eliminadas.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("⚠ Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Error interno al eliminar asistencias.");
        }
    }
    @PostMapping("importar")
    public ResponseEntity<Map<String, Object>> importarAsistencias(@RequestBody List<Asistencia> asistencias){
        Map<String, Object> respuesta = asistenciaService.guardarAsistencias(asistencias);
        return ResponseEntity.ok(respuesta);
    }
}
