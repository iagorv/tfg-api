package org.example.api.Controller;


import io.swagger.v3.oas.annotations.Operation;
import org.example.api.Entities.dtos.JuegoDetalleDTO;
import org.example.api.Entities.dtos.JuegoResumenDTO;
import org.example.api.Service.JuegoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "Obtener los 15 juegos con más reviews")
    @GetMapping("/populares")
    public ResponseEntity<List<JuegoResumenDTO>> obtenerJuegosPopulares() {
        List<JuegoResumenDTO> populares = juegoService.obtenerJuegosPopulares();
        return ResponseEntity.ok(populares);
    }
    @Operation(summary = "Obtener información detallada de un juego")
    @GetMapping("/{id}")
    public ResponseEntity<JuegoDetalleDTO> obtenerJuegoDetalle(
            @PathVariable Long id,
            @RequestParam(required = false) List<String> include) {

        JuegoDetalleDTO juegoDetalle = juegoService.obtenerJuegoDetalle(id);
        return ResponseEntity.ok(juegoDetalle);
    }


}