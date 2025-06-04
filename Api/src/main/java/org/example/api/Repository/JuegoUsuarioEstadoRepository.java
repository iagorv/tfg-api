package org.example.api.Repository;

import org.example.api.Entities.JuegoUsuarioEstado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JuegoUsuarioEstadoRepository extends JpaRepository<JuegoUsuarioEstado, Long> {
    Optional<JuegoUsuarioEstado> findByUsuarioIdAndJuegoId(Long usuarioId, Long juegoId);
}
