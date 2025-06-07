package org.example.api.Entities.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;


public class BitacoraDTO {
    private Long id;
    private Long juegoId;
    private String nombreJuego;
    private String entrada;

    private LocalDate fecha;

    public BitacoraDTO(Long id, Long juegoId, String nombreJuego, String entrada, LocalDate fecha) {
        this.id = id;
        this.juegoId = juegoId;
        this.nombreJuego = nombreJuego;
        this.entrada = entrada;

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
