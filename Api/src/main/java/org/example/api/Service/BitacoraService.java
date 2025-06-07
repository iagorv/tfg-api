package org.example.api.Service;


import jakarta.persistence.EntityNotFoundException;
import org.example.api.Entities.BitacoraJuego;
import org.example.api.Entities.Juego;
import org.example.api.Entities.Usuario;
import org.example.api.Entities.dtos.BitacoraCrearDTO;
import org.example.api.Entities.dtos.BitacoraDTO;
import org.example.api.Repository.BitacoraRepository;
import org.example.api.Repository.JuegoRepository;
import org.example.api.Repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BitacoraService {

    private final BitacoraRepository bitacoraRepository;
    private final UsuarioRepository usuarioRepository;
    private final JuegoRepository juegoRepository;

    public BitacoraService(BitacoraRepository bitacoraRepository, UsuarioRepository usuarioRepository, JuegoRepository juegoRepository) {
        this.bitacoraRepository = bitacoraRepository;
        this.usuarioRepository = usuarioRepository;
        this.juegoRepository = juegoRepository;
    }

    public void agregarEntrada(BitacoraCrearDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        Juego juego = juegoRepository.findById(dto.getJuegoId())
                .orElseThrow(() -> new EntityNotFoundException("Juego no encontrado"));

        BitacoraJuego entrada = new BitacoraJuego();
        entrada.setUsuario(usuario);
        entrada.setJuego(juego);
        entrada.setEntrada(dto.getEntrada());

        entrada.setFecha(dto.getFecha() != null ? dto.getFecha() : LocalDate.now());


        bitacoraRepository.save(entrada);
    }
    public Page<BitacoraDTO> obtenerBitacoraPaginadaDeUsuario(Long usuarioId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("fecha").descending());
        return bitacoraRepository.findByUsuarioId(usuarioId, pageable);
    }

    public void eliminarEntradaPorId(Long id) {
        if (!bitacoraRepository.existsById(id)) {
            throw new EntityNotFoundException("Entrada no encontrada con id: " + id);
        }
        bitacoraRepository.deleteById(id);
    }


}

