package org.example.api.Entities.dtos;

import java.sql.Timestamp;
import java.time.LocalDate;

public class ReviewCrearDTO {


    private Long juegoId;
    private Long usuarioId;
    private String reseña;
    private double nota;
    private LocalDate fechaCreacion;

    public ReviewCrearDTO() {}

    public ReviewCrearDTO(Long juegoId, Long usuarioId, String reseña, double nota, LocalDate  fechaCreacion) {
        this.juegoId = juegoId;
        this.usuarioId = usuarioId;
        this.reseña = reseña;
        this.nota = nota;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getJuegoId() {
        return juegoId;
    }

    public void setJuegoId(Long juegoId) {
        this.juegoId = juegoId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getReseña() {
        return reseña;
    }

    public void setReseña(String reseña) {
        this.reseña = reseña;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public LocalDate  getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate  fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}


