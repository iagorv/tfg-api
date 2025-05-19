package org.example.api.Service;


import org.example.api.Entities.Juego;
import org.example.api.Entities.dtos.JuegoDetalleDTO;
import org.example.api.Entities.dtos.JuegoResumenDTO;
import org.example.api.Repository.JuegoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JuegoService {


    private final JuegoRepository juegoRepository;

    public JuegoService(JuegoRepository juegoRepository) {
        this.juegoRepository = juegoRepository;
    }



    public List<JuegoResumenDTO> obtenerResumenDeJuegos() {
        List<Juego> juegos = juegoRepository.findAll();
        List<JuegoResumenDTO> resumenes = new ArrayList<>();

        for (Juego juego : juegos) {
            resumenes.add(new JuegoResumenDTO(juego.getId(), juego.getNombre()));
        }

        return resumenes;
    }

    public List<JuegoResumenDTO> obtenerJuegosPopulares() {
        List<Object[]> juegosPopulares = juegoRepository.findJuegosPopulares();
        List<JuegoResumenDTO> resumenes = new ArrayList<>();


        for (Object[] juego : juegosPopulares) {
            Long id = (Long) juego[0]; // El id del juego
            String nombre = (String) juego[1]; // El nombre del juego
            resumenes.add(new JuegoResumenDTO(id, nombre));
        }

        return resumenes;
    }

    public JuegoDetalleDTO obtenerJuegoDetalle(Long id) {

        Juego juego = juegoRepository.findById(id).orElseThrow(() -> new RuntimeException("Juego no encontrado"));


        String desarrollador = juego.getDesarrollador() != null ? juego.getDesarrollador().getNombre() : "Desconocido";


        List<String> generos = juego.getJuegoGeneros()
                .stream()
                .map(juegoGenero -> juegoGenero.getGenero().getNombre())
                .toList();


        List<String> plataformas = juego.getJuegoPlataformas()
                .stream()
                .map(juegoPlataforma -> juegoPlataforma.getPlataforma().getNombre())
                .toList();


        return new JuegoDetalleDTO(juego.getId(), juego.getNombre(), juego.getDescripcion(), desarrollador, juego.getAnioSalida(), generos, plataformas);
    }





}
