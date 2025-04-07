package com.cinetickets.serviciocinetickets.controller;

import com.cinetickets.serviciocinetickets.entities.AuthRequest;
import com.cinetickets.serviciocinetickets.entities.UserInfo;
import com.cinetickets.serviciocinetickets.repository.UserInfoRepository;
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

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/CineTickets-Service")
public class UserController {

    private UserInfoRepository userInfoRepository;
    private UserInfoService service; // inyecta una instancia del servicio UserInfoService, que se utiliza para gestionar la información del usuario en la base de datos.
    private JwtService jwtService; // Inyecta una instancia del servicio JwtService, que se utiliza para generar tokens JWT y gestionar la autenticación.

    private AuthenticationManager authenticationManager; // Inyecta el AuthenticationManager, que se utiliza para autenticar a los usuarios.


    @Autowired
    public UserController(UserInfoService service, JwtService jwtService, AuthenticationManager authenticationManager, UserInfoRepository userInfoRepository) {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userInfoRepository = userInfoRepository;
    }

    @PostMapping("/usuarios")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

    @GetMapping("/usuarios")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserInfo> ListarUsuarios() {
        return this.userInfoRepository.findAll();
    }

    @PutMapping("/usuarios")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserInfo actualizarUsuario(@RequestBody UserInfo usuario) {
        return this.userInfoRepository.save(usuario);
    }

    @GetMapping("/usuarios/{id}")
    public UserInfo buscarUsuario(@PathVariable int id) {
        Optional<UserInfo> usuario = this.userInfoRepository.findById(id);
        return usuario.orElse(null);
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
