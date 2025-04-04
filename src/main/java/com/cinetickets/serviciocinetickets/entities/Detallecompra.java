package com.cinetickets.serviciocinetickets.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "detallecompra")
public class Detallecompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Compra compra;
    @ManyToOne
    private Funcion funcion;
}
