package org.example.api.Entities.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;


public class BitacoraDTO {
    private Long id;
    private Long juegoId;
    private String nombreJuego;
    private String entrada;
    private BigDecimal horasJugadas;
    private LocalDate fecha;

    public BitacoraDTO(Long id, Long juegoId, String nombreJuego, String entrada, BigDecimal horasJugadas, LocalDate fecha) {
        this.id = id;
        this.juegoId = juegoId;
        this.nombreJuego = nombreJuego;
        this.entrada = entrada;
        this.horasJugadas = horasJugadas;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreJuego() {
        return nombreJuego;
    }

    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
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

    public Long getJuegoId() {
        return juegoId;
    }

    public void setJuegoId(Long juegoId) {
        this.juegoId = juegoId;
    }
}
