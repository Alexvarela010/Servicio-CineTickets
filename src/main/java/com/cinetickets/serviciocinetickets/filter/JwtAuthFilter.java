package com.cinetickets.serviciocinetickets.filter;


import com.cinetickets.serviciocinetickets.service.JwtService;
import com.cinetickets.serviciocinetickets.service.UserInfoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Actúa como un filtro para procesar las solicitudes en una aplicación web. Esta clase se asegura de que el filtro
 * se aplique una vez por cada solicitud.
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService; // Servicio de token jwt para validar el token y extraer el nombre de usuario del token

    @Autowired
    private UserInfoService userDetailsService; // Servicio de detalles de usuario para cargar los detalles del usuario


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) { // Esto verifica si el usuario no está autenticado actualmente en el sistema
            UserDetails userDetails = userDetailsService.loadUserByUsername(username); // Esto carga los detalles del usuario
            if (jwtService.validateToken(token, userDetails)) {  // Esto valida el token JWT
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Esto agrega información adicional sobre la solicitud de autenticación, como la dirección IP del cliente, al objeto authToken que representa la autenticación exitosa del usuario
                SecurityContextHolder.getContext().setAuthentication(authToken);  // Esto establece la autenticación en el contexto de seguridad de Spring
            }
        }

        filterChain.doFilter(request, response);
    }
}