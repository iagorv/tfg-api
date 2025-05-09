package org.example.api.Entities.dtos;



import java.time.Instant;
import java.time.LocalDate;

public class UsuarioInfoDTO {

    private Long id;
    private String nombre;
    private String email;
    private Instant fechaAlta;
    private LocalDate fechaNacimiento;
    private boolean premium;

    public UsuarioInfoDTO(Long id, String nombre, String email, Instant fechaAlta, LocalDate fechaNacimiento, boolean premium) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.fechaAlta = fechaAlta;
        this.fechaNacimiento = fechaNacimiento;
        this.premium = premium;
    }

    // Getters y Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Instant fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }
}
