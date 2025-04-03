package com.example.ceprevirtualbackend.controller;

import com.example.ceprevirtualbackend.dto.LoginRequest;
import com.example.ceprevirtualbackend.dto.UsuarioSesionDTO;
import com.example.ceprevirtualbackend.entity.Estudiante;
import com.example.ceprevirtualbackend.service.EstudianteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/auth")
//@CrossOrigin(origins = "http://localhost:5173") // Habilitar CORS para el frontend
public class AuthController {
    @Autowired
    private EstudianteService estudianteService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        boolean authentic = estudianteService.verificarCredenciales(loginRequest.getUser(), loginRequest.getPassword(), loginRequest.getRole());

        if (authentic) {
            Estudiante estudiante = estudianteService.getEstudianteByDni(loginRequest.getUser());

            // 🔥 Crear un DTO con solo los datos necesarios
            UsuarioSesionDTO usuarioSesion = new UsuarioSesionDTO(
                    estudiante.getNombre(),
                    estudiante.getApellidoPaterno(),
                    estudiante.getDni(),
                    estudiante.getCorreo(),
                    estudiante.getRole()
            );

            // 🔥 Guardar DTO en la sesión
            HttpSession session = request.getSession(true);
            session.setAttribute("user", usuarioSesion);

            // ✅ Configurar Spring Security Context con la autenticación
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    usuarioSesion, // Principal (usuario autenticado)
                    null, // No hay credenciales después de autenticar
                    //List.of(new SimpleGrantedAuthority(usuarioSesion.getRole())) // Lista de roles
                    List.of(new SimpleGrantedAuthority("ROLE_" + usuarioSesion.getRole().toUpperCase())) // Agregar prefijo ROLE_
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            // ✅ Verificar si el usuario realmente se guarda
            System.out.println("🔍 Usuario guardado en sesión: " + usuarioSesion);
            System.out.println("✅ Sesión guardada para usuario: " + estudiante.getDni());
            System.out.println("✅ ID de sesión: " + session.getId());

            return ResponseEntity.ok(estudiante);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Credenciales Incorrectas\"}");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate(); // 🔥 Cierra la sesión en el servidor
            SecurityContextHolder.clearContext(); // 🔥 Elimina la autenticación de Spring Security
            System.out.println("✅ Sesión cerrada correctamente.");
            return ResponseEntity.ok("{\"message\": \"Sesión cerrada correctamente\"}");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"No hay sesión activa\"}");
    }

    @GetMapping("/user")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            Object usuario = session.getAttribute("user");
            System.out.println("🔍 Sesión encontrada: " + session.getId() + " | Usuario: " + usuario);

            if (usuario != null) {
                return ResponseEntity.ok(usuario);
            }
        }

        System.out.println("⚠️ No hay sesión activa");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"No hay sesión activa\"}");
    }
}
