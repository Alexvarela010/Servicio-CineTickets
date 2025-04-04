package com.cinetickets.serviciocinetickets.controller;

import com.cinetickets.serviciocinetickets.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/CineTickets-Service")

public class PagoController {
    private PagoRepository repository;
    @Autowired
    public PagoController(PagoRepository repository) {
        this.repository = repository;
    }
}
