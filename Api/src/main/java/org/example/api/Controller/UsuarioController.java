package org.example.api.Controller;


import org.example.api.Entities.dtos.UsuarioInfoDTO;
import org.example.api.Service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UsuarioController {


    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioInfoDTO> obtenerInformacionUsuario(@PathVariable Long id) {
        UsuarioInfoDTO usuarioInfoDTO = usuarioService.obtenerInfoUsuarioPorId(id);
        return ResponseEntity.ok(usuarioInfoDTO);
    }


}
