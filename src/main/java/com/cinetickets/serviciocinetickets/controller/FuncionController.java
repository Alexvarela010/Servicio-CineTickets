package com.cinetickets.serviciocinetickets.controller;

import com.cinetickets.serviciocinetickets.entities.Detallecompra;
import com.cinetickets.serviciocinetickets.entities.Funcion;
import com.cinetickets.serviciocinetickets.repository.FuncionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/CineTickets-Service")
public class FuncionController {
    private FuncionRepository repository;

    @Autowired
    public FuncionController(FuncionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/funciones")
    public List<Funcion> listarfunciones() {
        return this.repository.findAll();
    }

    @GetMapping("/funciones/{id}")
    public Funcion buscarFuncion(@PathVariable int id) {
        Optional<Funcion> funcion=this.repository.findById(id);
        return funcion.orElse(null);
    }

    @PutMapping("/funciones") //Metodo que actualiza una funcion
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Funcion actualizarFuncion(@RequestBody Funcion funcion) {
        return this.repository.save(funcion);
    }

    @PostMapping("/funciones") //Metodo que guarda una funcion
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Funcion guardarFuncion(@RequestBody Funcion funcion) {
        return this.repository.save(funcion);
    }

    @DeleteMapping("/funciones/{id}") //Metodo que elimina una funcion por id
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void eliminarFuncion(@PathVariable int id) {
        Optional<Funcion> funcion = this.repository.findById(id);
        funcion.ifPresent(value -> this.repository.delete(value));
    }
}
