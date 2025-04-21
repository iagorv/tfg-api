package org.example.api.Service;


import org.example.api.Entities.Usuario;
import org.example.api.Entities.dtos.LoginDTO;
import org.example.api.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Buscar usuario por email
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    // Lógica de login (sin encriptación de contraseñas)
    public boolean login(LoginDTO loginDTO) {
        // Buscar al usuario por el email
        Usuario usuario = usuarioRepository.findByEmail(loginDTO.getEmail())
                .orElse(null);

        if (usuario != null) {
            // Comparar contraseñas (sin hashing)
            return usuario.getContraseña().equals(loginDTO.getPassword());
        }

        return false; // Si no lo encontramos o las contraseñas no coinciden
    }
}
