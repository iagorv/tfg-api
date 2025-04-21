package org.example.api.Controller;


import org.example.api.Entities.Juego;
import org.example.api.Repository.JuegoRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/juegos")
public class JuegoController {

    private final JuegoRepository juegoRepository;

    public JuegoController(JuegoRepository juegoRepository) {
        this.juegoRepository = juegoRepository;
    }

    // Endpoint: GET /api/juegos/nombres
    @GetMapping("/nombres")
    public List<String> obtenerNombresDeJuegos() {
        return juegoRepository.findAll()
                .stream()
                .map(Juego::getNombre)
                .collect(Collectors.toList());
    }
}