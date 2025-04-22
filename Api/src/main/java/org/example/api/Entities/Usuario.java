package org.example.api.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "`contraseña`", nullable = false)
    private String contraseña;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_alta", nullable = false)
    private Instant fechaAlta;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "activo", nullable = false)
    private Boolean activo = false;

    @OneToMany(mappedBy = "usuario")
    private Set<JuegoUsuarioEstado> juegoUsuarioEstados = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<Review> reviews = new LinkedHashSet<>();

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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Instant getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Instant fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Set<JuegoUsuarioEstado> getJuegoUsuarioEstados() {
        return juegoUsuarioEstados;
    }

    public void setJuegoUsuarioEstados(Set<JuegoUsuarioEstado> juegoUsuarioEstados) {
        this.juegoUsuarioEstados = juegoUsuarioEstados;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

}