package com.cinetickets.serviciocinetickets.controller;

import com.cinetickets.serviciocinetickets.entities.Detallecompra;
import com.cinetickets.serviciocinetickets.entities.Peliculas;
import com.cinetickets.serviciocinetickets.repository.DetalleCompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/CineTickets-Service")
public class DetalleCompraController {
     private DetalleCompraRepository repository;
     @Autowired
     public DetalleCompraController(DetalleCompraRepository repository) {
         this.repository = repository;
     }
    @GetMapping("/detallecompras") //Metodo que lista los detalle de compra
    public List<Detallecompra> listarPeliculas() {
        return this.repository.findAll();
    }

    @GetMapping("/detallecompras/{id}") //Metodo que busca un detalle de compra por id
    public Detallecompra buscarPelicula(@PathVariable int id) {
        Optional<Detallecompra> detallecompra=this.repository.findById(id);
        return detallecompra.orElse(null);
    }

    @DeleteMapping("/detallecompras/{id}") //Metodo que elimina un detalle de compra por id
    public void eliminarPelicula(@PathVariable int id) {
        Optional<Detallecompra> detallecompra = this.repository.findById(id);
        detallecompra.ifPresent(detallescompra -> this.repository.delete(detallescompra));
    }

    @PutMapping("/detallecompras") //Metodo que actualiza un detalle de compra
    public Detallecompra actualizarPelicula(@RequestBody Detallecompra detallecompra) {
        return this.repository.save(detallecompra);
    }

    @PostMapping("/detallecompras") //Metodo que guarda un detalle de compra
    public Detallecompra guardarPelicula(@RequestBody Detallecompra detallecompra) {
        return this.repository.save(detallecompra);
    }
}
