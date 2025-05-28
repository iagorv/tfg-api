package org.example.api.Repository;

import org.example.api.Entities.Review;
import org.example.api.Entities.Usuario;
import org.example.api.Entities.dtos.ReviewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("""
    SELECT new org.example.api.Entities.dtos.ReviewDTO(
        r.id,
        j.nombre,
        r.reseña,
        r.nota,
        j.id,
        r.fechaReview
    )
    FROM Review r
    JOIN r.juego j
    WHERE r.usuario.id = :usuarioId
    ORDER BY r.fechaReview DESC
    limit 3
    """)
    List<ReviewDTO> findTop3ByUsuarioId(@Param("usuarioId") Long usuarioId);
}
