package com.cinetickets.serviciocinetickets.controller;

import com.cinetickets.serviciocinetickets.entities.Peliculas;
import com.cinetickets.serviciocinetickets.repository.PeliculasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/CineTickets-Service")
public class PeliculasController {
    private PeliculasRepository repository;
    @Autowired
    public PeliculasController(PeliculasRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/peliculas") //Metodo que lista las peliculas
    public List<Peliculas> listarPeliculas() {
        return this.repository.findAll();
    }

    @GetMapping("/peliculas/{id}") //Metodo que busca una pelicula por id
    public Peliculas buscarPelicula(@PathVariable int id) {
        Optional<Peliculas> pelicula=this.repository.findById(id);
        return pelicula.orElse(null);
    }

    @DeleteMapping("/peliculas/{id}") //Metodo que elimina una pelicula por id
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void eliminarPelicula(@PathVariable int id) {
        Optional<Peliculas> pelicula = this.repository.findById(id);
        pelicula.ifPresent(peliculas -> this.repository.delete(peliculas));
    }

    @PutMapping("/peliculas") //Metodo que actualiza una pelicula
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Peliculas actualizarPelicula(@RequestBody Peliculas pelicula) {
        return this.repository.save(pelicula);
    }

    @PostMapping("/peliculas") //Metodo que guarda una pelicula
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Peliculas guardarPelicula(@RequestBody Peliculas pelicula) {
        return this.repository.save(pelicula);
    }
}