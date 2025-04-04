package com.cinetickets.serviciocinetickets.controller;

import com.cinetickets.serviciocinetickets.repository.DetalleCompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/CineTickets-Service")
public class DetalleCompraController {
     private DetalleCompraRepository repository;
     @Autowired
     public DetalleCompraController(DetalleCompraRepository repository) {
         this.repository = repository;
     }
}
