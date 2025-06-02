package org.example.api.Controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.example.api.Entities.dtos.DistribucionNotasDTO;
import org.example.api.Entities.dtos.ReviewConUsuarioDTO;
import org.example.api.Entities.dtos.ReviewCrearDTO;
import org.example.api.Entities.dtos.ReviewDTO;
import org.example.api.Service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {


    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Operation(summary = "Condeguir las últimas reviews de un usuario")
    @GetMapping("/usuario/{id}/ultimas")
    public ResponseEntity<List<ReviewDTO>> obtenerUltimasTresReviews(@PathVariable Long id) {
        List<ReviewDTO> reviews = reviewService.obtenerUltimasTresReviews(id);
        return ResponseEntity.ok(reviews);
    }


    @Operation(summary = "Guardar una nueva review")
    @PostMapping("/add")
    public ResponseEntity<?> agregarReview(@RequestBody ReviewCrearDTO reviewCreateDTO) {
        try {
            reviewService.guardarReview(reviewCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Review añadida exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }

    @Operation(summary = "Obtener reseñas de un juego con nombre de usuario")
    @GetMapping("/juego/{juegoId}/con-usuario")
    public ResponseEntity<List<ReviewConUsuarioDTO>> obtenerReviewsDeJuegoConUsuario(@PathVariable Long juegoId) {
        List<ReviewConUsuarioDTO> reviews = reviewService.obtenerUltimasReviewsDeJuegoConUsuario(juegoId);
        return ResponseEntity.ok(reviews);
    }

    @Operation(summary = "Obtener la distribución de notas para un juego")
    @GetMapping("/juego/{juegoId}/distribucion")
    public ResponseEntity<DistribucionNotasDTO> obtenerDistribucion(@PathVariable Long juegoId) {
        DistribucionNotasDTO distribucion = reviewService.obtenerDistribucionNotasPorJuego(juegoId);
        return ResponseEntity.ok(distribucion);
    }

    @Operation(summary = "Obtener reviews paginadas de un usuario")
    @GetMapping("/usuario/{id}/paginas")
    public ResponseEntity<Page<ReviewDTO>> obtenerReviewsPaginadas(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Page<ReviewDTO> reviewsPage = reviewService.obtenerReviewsPaginadasDeUsuario(id, page, size);
        return ResponseEntity.ok(reviewsPage);

    }


    @Operation(summary = "Eliminar una review por ID y usuario")
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> eliminarReview(
            @PathVariable Long reviewId,
            @RequestParam Long usuarioId
    ) {
        try {
            reviewService.eliminarReviewPorId(reviewId, usuarioId);
            return ResponseEntity.ok("Review eliminada exitosamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La review no existe.");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la review.");
        }
    }


    @Operation(summary = "Editar una review existente")
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> editarReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewCrearDTO reviewUpdateDTO,
            @RequestParam Long usuarioId
    ) {
        try {
            reviewService.editarReview(reviewId, reviewUpdateDTO, usuarioId);
            return ResponseEntity.ok("Review actualizada exitosamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La review no existe.");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }


        
}
