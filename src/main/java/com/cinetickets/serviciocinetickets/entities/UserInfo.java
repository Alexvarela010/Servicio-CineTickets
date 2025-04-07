package com.cinetickets.serviciocinetickets.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Usuarios")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String email;
    private String name;
    private String password;
    private String roles;
    private String telefono;
    private String estado;
}
