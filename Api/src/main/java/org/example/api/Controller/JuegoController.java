package org.example.api.Controller;


import io.swagger.v3.oas.annotations.Operation;
import org.example.api.Entities.Juego;
import org.example.api.Entities.dtos.JuegoResumenDTO;
import org.example.api.Repository.JuegoRepository;
import org.example.api.Service.JuegoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/juegos")
public class JuegoController {

    private final JuegoService juegoService;

    public JuegoController(JuegoService juegoService) {
        this.juegoService = juegoService;
    }
    @Operation(summary = "conseguir nombre e id juegos")
    @GetMapping("/resumen")
    public ResponseEntity<List<JuegoResumenDTO>> obtenerResumen() {
        List<JuegoResumenDTO> resumen = juegoService.obtenerResumenDeJuegos();
        return ResponseEntity.ok(resumen);
    }
}