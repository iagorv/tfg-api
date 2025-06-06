package org.example.api.Repository;

import org.example.api.Entities.BitacoraJuego;
import org.example.api.Entities.Juego;
import org.example.api.Entities.dtos.BitacoraDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BitacoraRepository extends JpaRepository<BitacoraJuego, Long> {

    @Query("""
SELECT new org.example.api.Entities.dtos.BitacoraDTO(
    b.id,
    j.nombre,
    b.entrada,
    b.horasJugadas,
    b.fecha
)
FROM BitacoraJuego b
JOIN b.juego j
WHERE b.usuario.id = :usuarioId
ORDER BY b.fecha DESC
""")
    Page<BitacoraDTO> findByUsuarioId(@Param("usuarioId") Long usuarioId, Pageable pageable);

}
