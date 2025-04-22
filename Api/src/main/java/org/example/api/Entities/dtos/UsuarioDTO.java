package org.example.api.Entities.dtos;

public class UsuarioDTO {
    private String email;
    private Long Id;
    private String nombre;

    public UsuarioDTO(Long Id, String nombre, String email) {
        this.Id = Id;
        this.nombre = nombre;
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



}
