package org.example.api.Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Table(name = "review")
public class Review {
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
    @JoinColumn(name = "usuario_id", nullable = false)
    private org.example.api.Entities.Usuario usuario;

    @Lob
    @Column(name = "`reseña`")
    private String reseña;

    @Column(name = "nota")
    private Integer nota;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_review")
    private Instant fechaReview;

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

    public org.example.api.Entities.Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(org.example.api.Entities.Usuario usuario) {
        this.usuario = usuario;
    }

    public String getReseña() {
        return reseña;
    }

    public void setReseña(String reseña) {
        this.reseña = reseña;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Instant getFechaReview() {
        return fechaReview;
    }

    public void setFechaReview(Instant fechaReview) {
        this.fechaReview = fechaReview;
    }

}