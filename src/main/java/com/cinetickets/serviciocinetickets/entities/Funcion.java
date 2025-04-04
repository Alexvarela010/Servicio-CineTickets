package com.cinetickets.serviciocinetickets.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "funcion")
public class Funcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Peliculas pelicula;
    private LocalDate fecha;
    private LocalTime hora;
    private String sala;
    private int disponibilidad;
}
