package com.cinetickets.serviciocinetickets.repository;

import com.cinetickets.serviciocinetickets.entities.Peliculas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculasRepository extends JpaRepository<Peliculas, Integer> {
}