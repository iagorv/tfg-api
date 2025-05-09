package org.example.api.Service;


import org.example.api.Entities.Usuario;
import org.example.api.Entities.dtos.LoginDTO;
import org.example.api.Entities.dtos.RegistroDTO;
import org.example.api.Entities.dtos.UsuarioDTO;
import org.example.api.Entities.dtos.UsuarioInfoDTO;
import org.example.api.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

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

    public UsuarioDTO registrarUsuario(RegistroDTO registroDTO) {
        // Comprobar si ya existe un usuario con ese email
        if (usuarioRepository.findByEmail(registroDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario registrado con ese email");
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(registroDTO.getNombre());
        nuevoUsuario.setEmail(registroDTO.getEmail());
        nuevoUsuario.setContraseña(registroDTO.getPassword());
        nuevoUsuario.setActivo(true);
        nuevoUsuario.setFechaAlta(Instant.now());
        nuevoUsuario.setFechaNacimiento(registroDTO.getFechaNacimiento());
        nuevoUsuario.setPremium(false);

        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

        return new UsuarioDTO(usuarioGuardado.getId(), usuarioGuardado.getNombre(), usuarioGuardado.getEmail());
    }
    public UsuarioInfoDTO obtenerInfoUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return new UsuarioInfoDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getFechaAlta(),
                usuario.getFechaNacimiento(),
                usuario.isPremium()
        );
    }
}
