package org.example.api.Entities.dtos;

import java.util.List;

public class JuegoDetalleDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String desarrollador;
    private Integer añoSalida;
    private List<String> generos;
    private List<String> plataformas;


    public JuegoDetalleDTO(Long id, String nombre, String descripcion, String desarrollador, Integer añoSalida, List<String> generos, List<String> plataformas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.desarrollador = desarrollador;
        this.añoSalida = añoSalida;
        this.generos = generos;
        this.plataformas = plataformas;
    }

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    public Integer getAñoSalida() {
        return añoSalida;
    }

    public void setAñoSalida(Integer añoSalida) {
        this.añoSalida = añoSalida;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public List<String> getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(List<String> plataformas) {
        this.plataformas = plataformas;
    }
}
