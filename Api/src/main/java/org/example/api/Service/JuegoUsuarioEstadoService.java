package org.example.api.Service;

import org.example.api.Entities.Juego;
import org.example.api.Entities.JuegoUsuarioEstado;
import org.example.api.Entities.Usuario;
import org.example.api.Entities.dtos.JuegoUsuarioEstadoDTO;
import org.example.api.Repository.JuegoRepository;
import org.example.api.Repository.JuegoUsuarioEstadoRepository;
import org.example.api.Repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class JuegoUsuarioEstadoService {

    private final JuegoUsuarioEstadoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final JuegoRepository juegoRepository;

    public JuegoUsuarioEstadoService(JuegoUsuarioEstadoRepository repository,
                                     UsuarioRepository usuarioRepository,
                                     JuegoRepository juegoRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.juegoRepository = juegoRepository;
    }

    public void guardarEstado(JuegoUsuarioEstadoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Juego juego = juegoRepository.findById(dto.getJuegoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Juego no encontrado"));

        JuegoUsuarioEstado estado = repository.findByUsuarioIdAndJuegoId(dto.getUsuarioId(), dto.getJuegoId())
                .orElse(new JuegoUsuarioEstado());

        estado.setUsuario(usuario);
        estado.setJuego(juego);
        estado.setEstado(dto.getEstado());
        estado.setFechaCambio(LocalDate.from(LocalDateTime.now()));

        repository.save(estado);
    }
    public String obtenerEstadoPorUsuarioYJuego(Long usuarioId, Long juegoId) {
        return repository.findByUsuarioIdAndJuegoId(usuarioId, juegoId)
                .map(JuegoUsuarioEstado::getEstado)
                .orElse(null);
    }

}
