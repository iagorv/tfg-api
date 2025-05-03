package org.example.api.Service;


import org.example.api.Entities.Juego;
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

        // Iteramos sobre los resultados de la subconsulta (cada fila es un Object[])
        for (Object[] juego : juegosPopulares) {
            Long id = (Long) juego[0]; // El id del juego
            String nombre = (String) juego[1]; // El nombre del juego
            // El número de reseñas está en el tercer campo (num_reviews)
            resumenes.add(new JuegoResumenDTO(id, nombre));
        }

        return resumenes;
    }



}
