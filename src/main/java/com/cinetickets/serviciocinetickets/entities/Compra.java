package com.cinetickets.serviciocinetickets.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "compra")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int compraId;
    @ManyToOne
    private UserInfo userInfo;
    @ManyToOne
    private Peliculas pelicula;
    private BigDecimal precioTotal;
    private Integer cantidadTickets;
    private LocalDate fechaCompra;


}
