package org.example.api.Service;


import org.example.api.Entities.Juego;
import org.example.api.Entities.dtos.JuegoResumenDTO;
import org.example.api.Repository.JuegoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

}
