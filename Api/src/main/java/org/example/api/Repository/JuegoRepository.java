package org.example.api.Repository;

import org.example.api.Entities.Juego;
import org.example.api.Entities.dtos.JuegoResumenDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface JuegoRepository extends JpaRepository<Juego, Long> {

    @Query(
            value = "SELECT j.id, j.nombre, (SELECT COUNT(r.id) FROM review r WHERE r.juego_id = j.id) AS num_reviews " +
                    "FROM juego j " +
                    "ORDER BY num_reviews DESC " +
                    "LIMIT 15",
            nativeQuery = true
    )
    List<Object[]> findJuegosPopulares();
}
