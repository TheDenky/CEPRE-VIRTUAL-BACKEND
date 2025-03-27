package com.example.ceprevirtualbackend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SessionLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(SessionLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Obtener la sesión sin crear una nueva
        if (session != null) {
            logger.info("🟢 Sesión ID: {}", session.getId());
            var attrNames = session.getAttributeNames();
            List<String> attributes = new ArrayList<>();
            while (attrNames.hasMoreElements()) {
                String attrName = attrNames.nextElement();
                attributes.add(attrName + " = " + session.getAttribute(attrName));
            }
            logger.info("🔹 Atributos de sesión: {}", attributes);

        } else {
            logger.warn("⚠️ No hay sesión activa.");
        }

        // Verificar si el usuario ya está autenticado
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            logger.info("✅ Usuario autenticado: {}", authentication.getName());
            if (authentication.getDetails() instanceof WebAuthenticationDetails details) {
                logger.info("🌍 IP: {}", details.getRemoteAddress());
            }
        } else {
            logger.warn("❌ Usuario NO autenticado.");
        }

        filterChain.doFilter(request, response);
    }
}
