package org.example.api.Service;


import org.example.api.Entities.Usuario;
import org.example.api.Entities.dtos.*;
import org.example.api.Repository.UsuarioRepository;
import org.example.api.util.PasswordUtil;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

        if (usuario != null) {
            String passwordHash = PasswordUtil.hashPassword(loginDTO.getPassword());

            if (usuario.getContraseña().equals(passwordHash)) {
                return new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.isPremium());

            }
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
        nuevoUsuario.setContraseña(PasswordUtil.hashPassword(registroDTO.getPassword()));
        nuevoUsuario.setActivo(true);
        nuevoUsuario.setFechaAlta(Instant.now());
        nuevoUsuario.setFechaNacimiento(registroDTO.getFechaNacimiento());
        nuevoUsuario.setPremium(false);

        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

        return new UsuarioDTO(usuarioGuardado.getId(), usuarioGuardado.getNombre(), usuarioGuardado.getEmail(),usuarioGuardado.isPremium());
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

    public UsuarioInfoDTO actualizarUsuario(Long id, UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Actualizamos los datos permitidos
        usuario.setNombre(usuarioUpdateDTO.getNombre());
        usuario.setFechaNacimiento(usuarioUpdateDTO.getFechaNacimiento());

        // Guardamos los cambios
        usuarioRepository.save(usuario);

        // Devolvemos el DTO actualizado
        return new UsuarioInfoDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getFechaAlta(),
                usuario.getFechaNacimiento(),
                usuario.isPremium()
        );
    }

    public void actualizarEstadoPremium(Long id, boolean nuevoEstado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setPremium(nuevoEstado);
        usuarioRepository.save(usuario);
    }

    public List<UsuarioDTO> buscarUsuarios(String query) {
        return usuarioRepository.findByNombreContainingIgnoreCase(query)
                .stream()
                .map(usuario -> new UsuarioDTO(
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getEmail(),
                        usuario.isPremium()
                ))
                .collect(Collectors.toList());
    }
    public void desactivarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setActivo(false);
        usuarioRepository.save(usuario);
    }
    public boolean estaActivo(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .map(Usuario::getActivo)
                .orElse(false);
    }





}
