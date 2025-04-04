package com.cinetickets.serviciocinetickets.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Peliculas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int peliculaId;
    private String titulo;
    private String descripcion;
    private String categoria;
    private String duracion;
    private String estado;
}