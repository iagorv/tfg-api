package org.example.api.Service;


import org.example.api.Entities.Juego;
import org.example.api.Entities.dtos.JuegoDetalleDTO;
import org.example.api.Entities.dtos.JuegoResumenDTO;
import org.example.api.Repository.JuegoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
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
        List<Object[]> results = juegoRepository.findJuegoDetalleById(id);

        if (results.isEmpty()) {
            return null;
        }

        Object[] result = results.get(0);
        System.out.println("result class: " + result.getClass());
        System.out.println("result[0] class: " + result[0].getClass());
        System.out.println("result[0]  " + result[0]);
        System.out.println("result[0]  " + result[1]);
        System.out.println("result[0]  " + result[2]);
        System.out.println("result: " + Arrays.deepToString(result));
        System.out.println("result length: " + result.length);


        System.out.println("hola caracola");
        Long juegoId = (Long) result[0];
        System.out.println(juegoId);
        String nombre = (String) result[1];
        System.out.println(nombre);
        String descripcion = (String) result[2];
        System.out.println(descripcion);
        String desarrollador = (String) result[3];
        System.out.println(desarrollador);
        Integer anioSalida = result[4] != null ? ((Number) result[4]).intValue() : null;
        System.out.println(anioSalida);

        // Los generos y plataformas vienen como cadenas separadas por coma, hay que convertir a lista
        List<String> generos = new ArrayList<>();
        if (result[5] != null) {
            generos = Arrays.asList(((String) result[5]).split(",\\s*"));
        }

        List<String> plataformas = new ArrayList<>();
        if (result[6] != null) {
            plataformas = Arrays.asList(((String) result[6]).split(",\\s*"));
        }

        return new JuegoDetalleDTO(juegoId, nombre, descripcion, desarrollador, anioSalida, generos, plataformas);
    }




}
