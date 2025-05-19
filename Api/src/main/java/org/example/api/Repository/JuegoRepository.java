package org.example.api.Repository;

import org.example.api.Entities.Juego;
import org.example.api.Entities.dtos.JuegoResumenDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    @Query(value = """
            SELECT j.id, j.nombre, j.descripcion, 
                   d.nombre AS desarrollador, 
                   j.año_salida,
                   (SELECT GROUP_CONCAT(g.nombre SEPARATOR ', ') 
                    FROM juego_genero jg 
                    INNER JOIN genero g ON jg.genero_id = g.id 
                    WHERE jg.juego_id = j.id) AS generos,
                   (SELECT GROUP_CONCAT(p.nombre SEPARATOR ', ') 
                    FROM juego_plataforma jp 
                    INNER JOIN plataforma p ON jp.plataforma_id = p.id 
                    WHERE jp.juego_id = j.id) AS plataformas
            FROM juego j 
            LEFT JOIN desarrollador d ON j.desarrollador_id = d.id
            WHERE j.id = :id
            """, nativeQuery = true)
    List<Object[]> findJuegoDetalleById(@Param("id") Long id);
}