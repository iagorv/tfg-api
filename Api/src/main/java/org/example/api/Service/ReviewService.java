package org.example.api.Service;


import jakarta.persistence.EntityNotFoundException;
import org.example.api.Entities.Juego;
import org.example.api.Entities.Review;
import org.example.api.Entities.Usuario;
import org.example.api.Entities.dtos.DistribucionNotasDTO;
import org.example.api.Entities.dtos.ReviewConUsuarioDTO;
import org.example.api.Entities.dtos.ReviewCrearDTO;
import org.example.api.Entities.dtos.ReviewDTO;
import org.example.api.Repository.JuegoRepository;
import org.example.api.Repository.ReviewRepository;
import org.example.api.Repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final JuegoRepository juegoRepository;
    private final UsuarioRepository usuarioRepository;

    public ReviewService(ReviewRepository reviewRepository, JuegoRepository juegoRepository, UsuarioRepository usuarioRepository) {
        this.reviewRepository = reviewRepository;
        this.juegoRepository = juegoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<ReviewDTO> obtenerUltimasTresReviews(Long usuarioId) {
        return reviewRepository.findTop3ByUsuarioId(usuarioId);
    }


    public void guardarReview(ReviewCrearDTO reviewCreateDTO) {
        if (reviewCreateDTO.getNota() < 0 || reviewCreateDTO.getNota() > 10) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 10.");
        }

        boolean esPremium = usuarioRepository.esPremium(reviewCreateDTO.getUsuarioId());
        int maxLongitud = esPremium ? 800 : 300 ;

        if (reviewCreateDTO.getReseña() != null && reviewCreateDTO.getReseña().length() > maxLongitud) {
            throw new IllegalArgumentException("La reseña supera el límite de " + maxLongitud + " caracteres.");
        }
        Juego juego = juegoRepository.findById(reviewCreateDTO.getJuegoId())
                .orElseThrow(() -> new EntityNotFoundException("Juego no encontrado"));

        Usuario usuario = usuarioRepository.findById(reviewCreateDTO.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        Review review = new Review();
        review.setJuego(juego);
        review.setUsuario(usuario);
        review.setResena(reviewCreateDTO.getReseña());
        review.setNota(reviewCreateDTO.getNota());
        review.setFechaReview(reviewCreateDTO.getFechaCreacion());
        reviewRepository.save(review);
    }

    public List<ReviewConUsuarioDTO> obtenerUltimasReviewsDeJuegoConUsuario(Long juegoId) {
        Pageable top6 = PageRequest.of(0, 6); // página 0, tamaño 6
        return reviewRepository.findByJuegoIdConUsuario(juegoId, top6);
    }


    public DistribucionNotasDTO obtenerDistribucionNotasPorJuego(Long juegoId) {
        List<Double> notas = reviewRepository.findNotasByJuegoId(juegoId);

        if (notas.isEmpty()) {
            return new DistribucionNotasDTO(0, new int[10]);
        }

        int[] conteos = new int[10];
        double suma = 0;

        for (Double nota : notas) {
            suma += nota;
            int indice = (int) Math.min(nota, 9); // Asegura que 10 no cause IndexOutOfBounds
            conteos[indice]++;
        }

        double promedio = suma / notas.size();
        return new DistribucionNotasDTO(promedio, conteos);
    }

    public Page<ReviewDTO> obtenerReviewsPaginadasDeUsuario(Long usuarioId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("fechaReview").descending());
        return reviewRepository.findByUsuarioId(usuarioId, pageable);
    }


    public void eliminarReviewPorId(Long reviewId, Long usuarioId){
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review no encontrada"));

        if (!review.getUsuario().getId().equals(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tienes permiso para eliminar esta review.");
        }


        reviewRepository.delete(review);
    }

    public void editarReview(Long reviewId, ReviewCrearDTO reviewUpdateDTO, Long usuarioId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review no encontrada"));

        if (!review.getUsuario().getId().equals(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tienes permiso para editar esta review.");
        }

        if (reviewUpdateDTO.getNota() < 0 || reviewUpdateDTO.getNota() > 10) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 10.");
        }

        boolean esPremium = usuarioRepository.esPremium(usuarioId);
        int maxLongitud = esPremium ? 800 : 300;

        if (reviewUpdateDTO.getReseña() != null && reviewUpdateDTO.getReseña().length() > maxLongitud) {
            throw new IllegalArgumentException("La reseña supera el límite de " + maxLongitud + " caracteres.");
        }

        // Solo actualizamos campos editables (nota y reseña)
        review.setNota(reviewUpdateDTO.getNota());
        review.setResena(reviewUpdateDTO.getReseña());
        review.setFechaReview(reviewUpdateDTO.getFechaCreacion()); // O la fecha actual

        reviewRepository.save(review);
    }


    public List<ReviewDTO> buscarReviews(String query) {
        return reviewRepository.buscarReviews(query)
                .stream()
                .map(review -> new ReviewDTO(
                        review.getId(),
                        review.getJuego().getNombre(),
                        review.getResena(),
                        review.getNota(),
                        review.getJuego().getId(),
                        review.getFechaReview()
                ))
                .collect(Collectors.toList());
    }
    public Page<ReviewConUsuarioDTO> obtenerTodasLasReviewsPaginadas(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("fechaReview").descending());
        return reviewRepository.findAllConUsuario(pageable);
    }



}
