package org.example.api.Repository;

import org.example.api.Entities.Review;
import org.example.api.Entities.Usuario;
import org.example.api.Entities.dtos.ReviewConUsuarioDTO;
import org.example.api.Entities.dtos.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    @Query("""
    SELECT new org.example.api.Entities.dtos.ReviewConUsuarioDTO(
        r.id,
        u.nombre,
        r.reseña,
        r.nota,
        j.id,
        r.fechaReview
    )
    FROM Review r
    JOIN r.juego j
    JOIN r.usuario u
    WHERE j.id = :juegoId
    ORDER BY r.fechaReview DESC
""")
    List<ReviewConUsuarioDTO> findByJuegoIdConUsuario(@Param("juegoId") Long juegoId, Pageable pageable);

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
""")
    Page<ReviewDTO> findByUsuarioId(@Param("usuarioId") Long usuarioId, Pageable pageable);





    @Query("SELECT r.nota FROM Review r WHERE r.juego.id = :juegoId")
    List<Double> findNotasByJuegoId(@Param("juegoId") Long juegoId);



}
