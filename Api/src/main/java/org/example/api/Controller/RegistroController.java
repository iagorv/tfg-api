package org.example.api.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.api.Entities.dtos.RegistroDTO;
import org.example.api.Entities.dtos.UsuarioDTO;
import org.example.api.Service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registro")
@Tag(name = "Registro", description = "Endpoints para registrar usuarios")
public class RegistroController {

    private final UsuarioService usuarioService;

    public RegistroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody RegistroDTO registroDTO) {
        try {
            UsuarioDTO usuarioDTO = usuarioService.registrarUsuario(registroDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

