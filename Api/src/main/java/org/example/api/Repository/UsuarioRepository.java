package org.example.api.Repository;

import org.example.api.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u.premium FROM Usuario u WHERE u.id = :id")
    Boolean esPremium(@Param("id") Long id);
}