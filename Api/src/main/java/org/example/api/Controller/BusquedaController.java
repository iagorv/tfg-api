package org.example.api.Controller;

import org.example.api.Service.JuegoService;
import org.example.api.Service.ReviewService;
import org.example.api.Service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/busqueda")
public class BusquedaController {
    // Inyectas todos los servicios que vayas a usar:
    private final JuegoService juegoService;
    private final UsuarioService usuarioService;
    private final ReviewService reviewService;

    public BusquedaController(JuegoService juegoService, UsuarioService usuarioService, ReviewService reviewService) {
        this.juegoService = juegoService;
        this.usuarioService = usuarioService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> buscar(@RequestParam("query") String query) {
        Map<String, Object> resultados = new HashMap<>();
        resultados.put("juegos", juegoService.buscarJuegos(query));
        resultados.put("usuarios", usuarioService.buscarUsuarios(query));
        resultados.put("reviews", reviewService.buscarReviews(query));
        return ResponseEntity.ok(resultados);
    }
}

