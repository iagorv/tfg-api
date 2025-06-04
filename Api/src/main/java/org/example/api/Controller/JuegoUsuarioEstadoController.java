package org.example.api.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.api.Entities.dtos.JuegoUsuarioEstadoDTO;
import org.example.api.Service.JuegoUsuarioEstadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estado-juego")
public class JuegoUsuarioEstadoController {

    private final JuegoUsuarioEstadoService service;

    public JuegoUsuarioEstadoController(JuegoUsuarioEstadoService service) {
        this.service = service;
    }


    @Operation(summary = "cambiar estado juego de un usuario ")
    @PostMapping
    public ResponseEntity<String> asignarEstado(@RequestBody JuegoUsuarioEstadoDTO dto) {
        service.guardarEstado(dto);
        return ResponseEntity.ok("Estado asignado correctamente");
    }



    @Operation(summary = "Obtener estado juego de un usuario ")
    @GetMapping("/{juegoId}")
    public ResponseEntity<String> obtenerEstado(
            @PathVariable Long juegoId,
            @RequestParam Long usuarioId
    ) {
        String estado = service.obtenerEstadoPorUsuarioYJuego(usuarioId, juegoId);
        if (estado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sin estado");
        }
        return ResponseEntity.ok(estado);
    }

}

