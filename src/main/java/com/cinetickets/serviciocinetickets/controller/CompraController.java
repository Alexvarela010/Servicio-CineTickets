package com.cinetickets.serviciocinetickets.controller;

import com.cinetickets.serviciocinetickets.entities.Compra;
import com.cinetickets.serviciocinetickets.repository.CompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/CineTickets-Service")
public class CompraController {
     private CompraRepository repository;
     @Autowired
     public CompraController(CompraRepository repository) {
         this.repository = repository;
     }
    @GetMapping("/compras") //Metodo que lista las compras
    public List<Compra> listarCompras() {
        return this.repository.findAll();
    }

    @GetMapping("/compras/{id}") //Metodo que busca una compra por id
    public Compra buscarCompra(@PathVariable int id) {
         Optional <Compra> compra=this.repository.findById(id);
         return compra.orElse(null);
    }

    @PutMapping("/compras") //Metodo que actualiza una compra
    public Compra actualizarCompra(@RequestBody Compra compra) {
        return this.repository.save(compra);
    }

    @PostMapping("/compras") //Metodo que guarda una compra
    public Compra guardarCompra(@RequestBody Compra compra) {
        return this.repository.save(compra);
    }

    @DeleteMapping("/compras/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void eliminarCompra(@PathVariable int id) {
        Optional<Compra> compras = this.repository.findById(id);
        compras.ifPresent(Compra -> this.repository.delete(Compra));
    }
}
