package org.example.api.Entities.dtos;

import java.time.Instant;

public class ReviewDTO {
    private Long id;
    private String tituloJuego;
    private String reseña;
    private Double nota;
    private Long juegoId;
    private Instant fechaReview;

    public ReviewDTO(Long id, String tituloJuego, String reseña, Double nota, Long juegoId, Instant fechaReview) {
        this.id = id;
        this.tituloJuego = tituloJuego;
        this.reseña = reseña;
        this.nota = nota;
        this.juegoId = juegoId;
        this.fechaReview = fechaReview;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTituloJuego() {
        return tituloJuego;
    }

    public void setTituloJuego(String tituloJuego) {
        this.tituloJuego = tituloJuego;
    }

    public String getReseña() {
        return reseña;
    }

    public void setReseña(String reseña) {
        this.reseña = reseña;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Long getJuegoId() {
        return juegoId;
    }

    public void setJuegoId(Long juegoId) {
        this.juegoId = juegoId;
    }

    public Instant getFechaReview() {
        return fechaReview;
    }

    public void setFechaReview(Instant fechaReview) {
        this.fechaReview = fechaReview;
    }
}

