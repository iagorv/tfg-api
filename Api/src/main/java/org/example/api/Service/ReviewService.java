package org.example.api.Service;


import org.example.api.Entities.dtos.ReviewDTO;
import org.example.api.Repository.ReviewRepository;
import org.example.api.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<ReviewDTO> obtenerUltimasTresReviews(Long usuarioId) {
        return reviewRepository.findTop3ByUsuarioId(usuarioId);
    }
}
