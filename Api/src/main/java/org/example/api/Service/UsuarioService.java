package org.example.api.Service;


import org.example.api.Entities.Usuario;
import org.example.api.Entities.dtos.LoginDTO;
import org.example.api.Entities.dtos.UsuarioDTO;
import org.example.api.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }



    public UsuarioDTO login(LoginDTO loginDTO) {
        // Buscar al usuario por el email
        Usuario usuario = usuarioRepository.findByEmail(loginDTO.getEmail())
                .orElse(null);

        if (usuario != null && usuario.getContraseña().equals(loginDTO.getPassword())){

            return new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getEmail());
        }

        return null;
    }
}
