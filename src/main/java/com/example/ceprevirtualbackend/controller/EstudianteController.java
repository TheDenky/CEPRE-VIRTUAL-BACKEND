package com.example.ceprevirtualbackend.controller;

import com.example.ceprevirtualbackend.entity.Estudiante;
import com.example.ceprevirtualbackend.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    //@PostMapping("/importar")
    //public ResponseEntity<String> importarEstudiantes(@RequestBody List<Estudiante> estudiantes) {
    //    estudianteService.guardarEstudiantes(estudiantes);
    //    return ResponseEntity.ok("Estudiantes importados exitosamente");
    //}

    //@PostMapping("/importar")
    //public ResponseEntity<String> importarEstudiantes(@RequestBody List<Estudiante> estudiantes) {
    //    List<String> errores = estudianteService.guardarEstudiantes(estudiantes);
    //    if (!errores.isEmpty()) {
   //         return ResponseEntity.badRequest().body("Algunos estudiantes no se guardaron: " + String.join(", ", errores));
    //    }
    //    return ResponseEntity.ok("Estudiantes importados exitosamente");
    //}
    @PostMapping("/importar")
    public ResponseEntity<Map<String, Object>> importarEstudiantes(@RequestBody List<Estudiante> estudiantes) {
        Map<String, Object> respuesta = new HashMap<>();

        try {
            respuesta = estudianteService.guardarEstudiantes(estudiantes);
            return ResponseEntity.ok(respuesta); // Siempre responde con 200 OK
        } catch (Exception e) {
            respuesta.put("error", "Error interno del servidor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }
}
