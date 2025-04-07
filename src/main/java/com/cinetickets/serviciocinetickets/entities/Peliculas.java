package com.cinetickets.serviciocinetickets.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "peliculas")
public class Peliculas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int peliculaId;
    private String titulo;
    private String descripcion;
    private String categoria;
    private String duracion;
    private String estado;
    private String imagen;
    private String imagenCarrusel;
    private BigDecimal precioEntrada;
}