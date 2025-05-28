package org.example.api.Entities.dtos;

public class UsuarioDTO {
    private String email;
    private Long Id;
    private String nombre;
    private boolean esPremium;

    public UsuarioDTO(Long Id, String nombre, String email, boolean esPremium) {
        this.Id = Id;
        this.nombre = nombre;
        this.email = email;
        this.esPremium = esPremium;
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

    public boolean isEsPremium() {
        return esPremium;
    }

    public void setEsPremium(boolean esPremium) {
        this.esPremium = esPremium;
    }
}
