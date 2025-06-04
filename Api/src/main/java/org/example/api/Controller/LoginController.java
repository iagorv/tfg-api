package org.example.api.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.api.Entities.dtos.LoginDTO;
import org.example.api.Entities.dtos.UsuarioDTO;
import org.example.api.Service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/login")
    @Tag(name = "Login", description = "Endpoints para el login de usuario")
    public class LoginController {

        private final UsuarioService usuarioService;

        public LoginController(UsuarioService usuarioService) {
            this.usuarioService = usuarioService;
        }
        @PostMapping
        public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
            UsuarioDTO usuarioDTO = usuarioService.login(loginDTO);

            if (usuarioDTO != null) {
                boolean estaActivo = usuarioService.estaActivo(usuarioDTO.getId());

                if (estaActivo) {
                    return ResponseEntity.ok(usuarioDTO);
                } else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario no está activado.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
            }
        }

    }


