package org.example.api.Entities.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BitacoraCrearDTO {
    private Long usuarioId;
    private Long juegoId;
    private String entrada;
    private BigDecimal horasJugadas;
    private LocalDate fecha;



    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getJuegoId() {
        return juegoId;
    }

    public void setJuegoId(Long juegoId) {
        this.juegoId = juegoId;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public BigDecimal getHorasJugadas() {
        return horasJugadas;
    }

    public void setHorasJugadas(BigDecimal horasJugadas) {
        this.horasJugadas = horasJugadas;
    }
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
