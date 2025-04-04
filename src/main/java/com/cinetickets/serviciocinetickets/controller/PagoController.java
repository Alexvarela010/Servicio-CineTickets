package com.cinetickets.serviciocinetickets.controller;

import com.cinetickets.serviciocinetickets.entities.Pago;
import com.cinetickets.serviciocinetickets.entities.Peliculas;
import com.cinetickets.serviciocinetickets.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/CineTickets-Service")

public class PagoController {
    private PagoRepository repository;
    @Autowired
    public PagoController(PagoRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/pagos") //Metodo que lista los pagos
    public List<Pago> listarPagos() {
        return this.repository.findAll();
    }

    @GetMapping("/pagos/{id}") //Metodo que busca un pago por id
    public Pago buscarPago(@PathVariable int id) {
        Optional<Pago> pago=this.repository.findById(id);
        return pago.orElse(null);
    }


    @PutMapping("/pagos") //Metodo que actualiza un pago
    public Pago actualizarPelicula(@RequestBody Pago pago) {
        return this.repository.save(pago);
    }

    @PostMapping("/pagos") //Metodo que guarda un pago
    public Pago guardarPelicula(@RequestBody Pago pago) {
        return this.repository.save(pago);
    }
}
