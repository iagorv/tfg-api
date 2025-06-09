package org.example.api.Controller;


import io.swagger.v3.oas.annotations.Operation;
import org.example.api.Entities.Juego;
import org.example.api.Entities.dtos.JuegoDetalleDTO;
import org.example.api.Entities.dtos.JuegoNombreDTO;
import org.example.api.Entities.dtos.JuegoResumenDTO;
import org.example.api.Service.JuegoService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> obtenerJuegoDetalle(@PathVariable Long id) {
        JuegoDetalleDTO detalle = juegoService.obtenerJuegoDetalle(id);

        if (detalle != null) {
            return ResponseEntity.ok(detalle); 
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Juego con ID " + id + " no encontrado");
        }
    }
    @Operation(summary = "Obtener solo el nombre del juego")
    @GetMapping("/{id}/nombre")
    public ResponseEntity<?> obtenerNombreJuego(@PathVariable Long id) {
        JuegoNombreDTO dto = juegoService.obtenerNombrePorId(id);

        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Juego con ID " + id + " no encontrado");
        }
    }

    @Operation(summary = "Obtener juegos similares por género")
    @GetMapping("/{id}/similares")
    public ResponseEntity<List<JuegoResumenDTO>> obtenerJuegosSimilares(@PathVariable Long id) {
        List<JuegoResumenDTO> similares = juegoService.obtenerJuegosSimilares(id);
        return ResponseEntity.ok(similares);
    }

    @Operation(summary = "Obtener juegos paginados")
    @GetMapping("/paginas")
    public ResponseEntity<Page<JuegoResumenDTO>> obtenerJuegosPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {

        Page<JuegoResumenDTO> juegosPage = juegoService.obtenerJuegosPaginados(page, size);
        return ResponseEntity.ok(juegosPage);
    }

    
    @GetMapping("/games/{gameId}/portadas")
    public ResponseEntity<List<String>> obtenerPortadas(@PathVariable Long gameId) {
        List<String> portadas = new ArrayList<>();

        // portada oficial
        portadas.add("http://localhost:8080/img/games/" + gameId + ".png");

        // portadas alternativas
        File folder = new File("src/main/resources/static/img/portadas_alt/");
        File[] files = folder.listFiles((dir, name) -> name.startsWith(gameId + "_") && (name.endsWith(".png") || name.endsWith(".jpg")));
        if (files != null) {
            for (File file : files) {
                portadas.add("http://localhost:8080/img/portadas_alt/" + file.getName());
            }
        }

        return ResponseEntity.ok(portadas);
    }




}