package org.example.api.Entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "plataforma")
public class Plataforma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "plataforma")
    private Set<org.example.api.Entities.JuegoPlataforma> juegoPlataformas = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<org.example.api.Entities.JuegoPlataforma> getJuegoPlataformas() {
        return juegoPlataformas;
    }

    public void setJuegoPlataformas(Set<org.example.api.Entities.JuegoPlataforma> juegoPlataformas) {
        this.juegoPlataformas = juegoPlataformas;
    }

}