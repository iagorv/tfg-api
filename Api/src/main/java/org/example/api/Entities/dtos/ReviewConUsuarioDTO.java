package org.example.api.Entities.dtos;

import java.time.LocalDate;

public class ReviewConUsuarioDTO {
    private Long id;
    private String usuarioNombre;
    private String reseña;
    private Double nota;
    private Long juegoId;
    private LocalDate fechaReview;


    public ReviewConUsuarioDTO(Long id, String usuarioNombre, String reseña, Double nota, Long juegoId, LocalDate fechaReview) {
        this.id = id;
        this.usuarioNombre = usuarioNombre;
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

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
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

    public LocalDate getFechaReview() {
        return fechaReview;
    }

    public void setFechaReview(LocalDate fechaReview) {
        this.fechaReview = fechaReview;
    }
}

