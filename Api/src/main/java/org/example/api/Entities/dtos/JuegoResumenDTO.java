package org.example.api.Entities.dtos;

public class JuegoResumenDTO {
    private Long id;
    private String nombre;

    public JuegoResumenDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
