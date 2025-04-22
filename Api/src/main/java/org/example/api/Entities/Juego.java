package org.example.api.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.api.Desarrollador;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "juego")
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 150)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "desarrollador_id")
    private Desarrollador desarrollador;

    @Column(name = "anio_salida")
    private Integer anioSalida;

    @OneToMany(mappedBy = "juego")
    private Set<JuegoGenero> juegoGeneros = new LinkedHashSet<>();

    @OneToMany(mappedBy = "juego")
    private Set<JuegoPlataforma> juegoPlataformas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "juego")
    private Set<JuegoUsuarioEstado> juegoUsuarioEstados = new LinkedHashSet<>();

    @OneToMany(mappedBy = "juego")
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Desarrollador getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(Desarrollador desarrollador) {
        this.desarrollador = desarrollador;
    }

    public Integer getAnioSalida() {
        return anioSalida;
    }

    public void setAnioSalida(Integer anioSalida) {
        this.anioSalida = anioSalida;
    }

    public Set<JuegoGenero> getJuegoGeneros() {
        return juegoGeneros;
    }

    public void setJuegoGeneros(Set<JuegoGenero> juegoGeneros) {
        this.juegoGeneros = juegoGeneros;
    }

    public Set<JuegoPlataforma> getJuegoPlataformas() {
        return juegoPlataformas;
    }

    public void setJuegoPlataformas(Set<JuegoPlataforma> juegoPlataformas) {
        this.juegoPlataformas = juegoPlataformas;
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