package com.example.ceprevirtualbackend.service;

import com.example.ceprevirtualbackend.entity.Estudiante;
import com.example.ceprevirtualbackend.entity.EstudianteCiclo;
import com.example.ceprevirtualbackend.repository.CicloRepository;
import com.example.ceprevirtualbackend.repository.EstudianteCicloRepository;
import com.example.ceprevirtualbackend.repository.EstudianteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EstudianteCicloService {
    @Autowired
    EstudianteCicloRepository estudianteCicloRepository;
    @Autowired
    EstudianteRepository estudianteRepository;
    @Autowired
    CicloRepository cicloRepository;

    public List<EstudianteCiclo> getEstudiantesCiclos(){
        return estudianteCicloRepository.findAll();
    }
    public Optional<EstudianteCiclo> getEstudianteCiclo(Long id){
        return estudianteCicloRepository.findById(id);
    }
    public List<EstudianteCiclo> getByCiclo(Long cicloId) {
        return estudianteCicloRepository.findByCiclo_CicloId(cicloId);
    }
    public void saveOrUdateEstudianteCiclo(EstudianteCiclo estudianteCiclo){
        estudianteCicloRepository.save(estudianteCiclo);
    }
    public void deleteEstudianteCiclo(Long id){
        estudianteCicloRepository.deleteById(id);
    }
    // ✅ Método corregido para matricular estudiantes
    public Map<String, Object> matricularEstudiantes(Map<String, Object> request) {
        Map<String, Object> respuesta = new HashMap<>();
        List<String> errores = new ArrayList<>();
        int exitos = 0;

        // ✅ 1. Convertir `cicloId` asegurando que sea un `Long`
        Long cicloId;
        Object cicloIdObj = request.get("cicloId");
        if (cicloIdObj instanceof Number) {
            cicloId = ((Number) cicloIdObj).longValue(); // Convierte sin errores
        } else {
            cicloId = Long.parseLong(cicloIdObj.toString()); // Convierte si es String
        }

        // ✅ 2. Convertir `dniList` asegurando que sean Strings
        List<String> dniList = new ArrayList<>();
        Object dniListObj = request.get("dniList");
        if (dniListObj instanceof List) {
            for (Object dni : (List<?>) dniListObj) {
                dniList.add(String.valueOf(dni)); // Convierte cada DNI a String
            }
        }

        // ✅ 3. Procesar cada estudiante por DNI
        for (String dni : dniList) {
            Optional<Estudiante> estudianteOpt = estudianteRepository.findByDni(dni);

            if (estudianteOpt.isPresent()) {
                Estudiante estudiante = estudianteOpt.get();

                // ✅ Verificar si ya existe una matrícula con este estudianteId y cicloId
                boolean yaMatriculado = estudianteCicloRepository.existsByEstudiante_EstudianteIdAndCiclo_CicloId(
                        estudiante.getEstudianteId(), cicloId
                );

                if (yaMatriculado) {
                    errores.add("El estudiante con DNI " + dni + " ya está matriculado en este ciclo.");
                } else {
                    // ✅ Si no está matriculado, proceder con la matrícula
                    EstudianteCiclo ec = new EstudianteCiclo();
                    ec.setEstudiante(estudiante);
                    ec.setCiclo(cicloRepository.findById(cicloId).orElse(null));
                    estudianteCicloRepository.save(ec);
                    exitos++;
                }
            } else {
                errores.add("DNI no encontrado: " + dni);
            }
        }

        respuesta.put("exitos", exitos);
        respuesta.put("errores", errores);
        return respuesta;
    }
    public List<Map<String, Object>> getEstudianteCicloByDniAndCiclo(Long cicloId, List<String> dniList) {
        List<Map<String, Object>> resultado = new ArrayList<>();

        for (String dni : dniList) {
            Optional<Estudiante> estudianteOpt = estudianteRepository.findByDni(dni);

            if (estudianteOpt.isPresent()) {
                Long estudianteId = estudianteOpt.get().getEstudianteId();
                Optional<EstudianteCiclo> estudianteCicloOpt =
                        estudianteCicloRepository.findByEstudiante_EstudianteIdAndCiclo_CicloId(estudianteId, cicloId);

                if (estudianteCicloOpt.isPresent()) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("dni", dni);
                    data.put("estudianteCicloId", estudianteCicloOpt.get().getEstudianteCicloId());
                    resultado.add(data);
                } else {
                    System.out.println("⚠️ No se encontró estudianteCiclo para DNI: " + dni);
                }
            } else {
                System.out.println("⚠️ No se encontró estudiante con DNI: " + dni);
            }
        }

        System.out.println("✅ Datos generados para enviar: " + resultado); // Log en backend

        return resultado;
    }

    @Transactional
    public void eliminarMatriculasPorCiclo(Long cicloId) {
        estudianteCicloRepository.deleteByCicloId(cicloId);
    }

}
