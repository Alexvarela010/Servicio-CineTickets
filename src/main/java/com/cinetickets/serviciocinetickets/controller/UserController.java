package com.cinetickets.serviciocinetickets.controller;

import com.cinetickets.serviciocinetickets.entities.AuthRequest;
import com.cinetickets.serviciocinetickets.entities.UserInfo;
import com.cinetickets.serviciocinetickets.service.JwtService;
import com.cinetickets.serviciocinetickets.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/CineTickets-Service")
public class UserController {


    private UserInfoService service; // inyecta una instancia del servicio UserInfoService, que se utiliza para gestionar la información del usuario en la base de datos.

    private JwtService jwtService; // Inyecta una instancia del servicio JwtService, que se utiliza para generar tokens JWT y gestionar la autenticación.

    private AuthenticationManager authenticationManager; // Inyecta el AuthenticationManager, que se utiliza para autenticar a los usuarios.


    @Autowired
    public UserController(UserInfoService service, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }



    @PostMapping("/addNewUser")
    public UserInfo addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "bienvenido a perfil usuario";
    }


    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Perfil administrador";
    }


    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

}
