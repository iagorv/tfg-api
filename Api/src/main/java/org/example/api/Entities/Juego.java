package org.example.api.Entities;

import jakarta.persistence.*;
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

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "desarrollador", length = 100)
    private String desarrollador;

    @Column(name = "anio_salida")
    private Integer anioSalida;

    @OneToMany(mappedBy = "juego")
    private Set<org.example.api.Entities.JuegoPlataforma> juegoPlataformas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "juego")
    private Set<org.example.api.Entities.JuegoUsuarioEstado> juegoUsuarioEstados = new LinkedHashSet<>();

    @OneToMany(mappedBy = "juego")
    private Set<org.example.api.Entities.Review> reviews = new LinkedHashSet<>();

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



    public Integer getAnioSalida() {
        return anioSalida;
    }

    public void setAnioSalida(Integer anioSalida) {
        this.anioSalida = anioSalida;
    }

    public Set<org.example.api.Entities.JuegoPlataforma> getJuegoPlataformas() {
        return juegoPlataformas;
    }

    public void setJuegoPlataformas(Set<org.example.api.Entities.JuegoPlataforma> juegoPlataformas) {
        this.juegoPlataformas = juegoPlataformas;
    }

    public Set<org.example.api.Entities.JuegoUsuarioEstado> getJuegoUsuarioEstados() {
        return juegoUsuarioEstados;
    }

    public void setJuegoUsuarioEstados(Set<org.example.api.Entities.JuegoUsuarioEstado> juegoUsuarioEstados) {
        this.juegoUsuarioEstados = juegoUsuarioEstados;
    }

    public Set<org.example.api.Entities.Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<org.example.api.Entities.Review> reviews) {
        this.reviews = reviews;
    }

}