package org.example.api.Service;


import jakarta.persistence.EntityNotFoundException;
import org.example.api.Entities.Juego;
import org.example.api.Entities.Review;
import org.example.api.Entities.Usuario;
import org.example.api.Entities.dtos.ReviewCrearDTO;
import org.example.api.Entities.dtos.ReviewDTO;
import org.example.api.Repository.JuegoRepository;
import org.example.api.Repository.ReviewRepository;
import org.example.api.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        review.setReseña(reviewCreateDTO.getReseña());
        review.setNota(reviewCreateDTO.getNota());
        review.setFechaReview(reviewCreateDTO.getFechaCreacion());
        reviewRepository.save(review);
    }
}
