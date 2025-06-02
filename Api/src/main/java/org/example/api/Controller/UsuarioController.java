package org.example.api.Controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.example.api.Entities.dtos.*;
import org.example.api.Service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/api/user")
public class UsuarioController {


    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Conseguir informacion de usuario")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioInfoDTO> obtenerInformacionUsuario(@PathVariable Long id) {
        UsuarioInfoDTO usuarioInfoDTO = usuarioService.obtenerInfoUsuarioPorId(id);
        return ResponseEntity.ok(usuarioInfoDTO);
    }

    @Operation(summary = "Actualizar usuario")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(
            @PathVariable Long id,
            @RequestBody @Valid UsuarioUpdateDTO usuarioUpdateDTO) {
        if (!esMayorDe16(usuarioUpdateDTO.getFechaNacimiento())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Debes ser mayor de 16 años para actualizar el usuario.");
        } else {
            UsuarioInfoDTO usuarioActualizado = usuarioService.actualizarUsuario(id, usuarioUpdateDTO);
            return ResponseEntity.ok(usuarioActualizado);
        }
    }

    @Operation(summary = "Registro de usuario")
    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody RegistroDTO registroDTO) {
        try {
            validarRegistro(registroDTO);
            UsuarioDTO usuarioDTO = usuarioService.registrarUsuario(registroDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    private void validarRegistro(RegistroDTO registroDTO) {
        if (!registroDTO.getPassword().equals(registroDTO.getConfirmPassword())) {
            throw new RuntimeException("Las contraseñas no coinciden.");
        }
        if (!esMayorDe16(registroDTO.getFechaNacimiento())) {
            throw new RuntimeException("Debes ser mayor de 16 años para registrarte.");
        }
    }

    private boolean esMayorDe16(LocalDate fechaNacimiento) {
        return ChronoUnit.YEARS.between(fechaNacimiento, LocalDate.now()) >= 16;
    }

    @Operation(summary = "Actualizar estado premium del usuario")
    @PatchMapping("/{id}/premium")
    public ResponseEntity<?> actualizarPremium(
            @PathVariable Long id,
            @RequestBody PremiumUpdateDTO premiumUpdateDTO) {
        try {
            usuarioService.actualizarEstadoPremium(id, premiumUpdateDTO.isPremium());
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }




}
