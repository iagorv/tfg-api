package org.example.api.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.api.Entities.Usuario;
import org.example.api.Entities.dtos.LoginDTO;
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

    @Operation(summary = "Login de usuario")
    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        boolean esValido = usuarioService.login(loginDTO);

        if (esValido) {
            return ResponseEntity.ok("Login exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }
}


