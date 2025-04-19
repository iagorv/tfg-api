package org.example.api.Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Table(name = "juego_usuario_estado")
public class JuegoUsuarioEstado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario_id", nullable = false)
    private org.example.api.Entities.Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "juego_id", nullable = false)
    private org.example.api.Entities.Juego juego;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_cambio")
    private Instant fechaCambio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public org.example.api.Entities.Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(org.example.api.Entities.Usuario usuario) {
        this.usuario = usuario;
    }

    public org.example.api.Entities.Juego getJuego() {
        return juego;
    }

    public void setJuego(org.example.api.Entities.Juego juego) {
        this.juego = juego;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Instant getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Instant fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

}