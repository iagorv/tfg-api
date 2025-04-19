package org.example.api.Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "juego_plataforma")
public class JuegoPlataforma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "juego_id", nullable = false)
    private org.example.api.Entities.Juego juego;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "plataforma_id", nullable = false)
    private org.example.api.Entities.Plataforma plataforma;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public org.example.api.Entities.Juego getJuego() {
        return juego;
    }

    public void setJuego(org.example.api.Entities.Juego juego) {
        this.juego = juego;
    }

    public org.example.api.Entities.Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(org.example.api.Entities.Plataforma plataforma) {
        this.plataforma = plataforma;
    }

}