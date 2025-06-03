package org.example.api.Controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.example.api.Entities.dtos.*;
import org.example.api.Service.UsuarioService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @PostMapping("/usuarios/{id}/foto")
    public String subirFotoPerfil(@PathVariable Long id,
                                  @RequestParam("fotoPerfil") MultipartFile archivo,
                                  RedirectAttributes redirectAttributes) {
        if (archivo.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "No se seleccionó ningún archivo.");
            return "redirect:/usuarios/" + id;
        }

        String contentType = archivo.getContentType();
        if (contentType == null ||
                !(contentType.equals("image/jpeg") ||
                        contentType.equals("image/png") ||
                        contentType.equals("image/webp"))) {

            redirectAttributes.addFlashAttribute("error", "Formato no válido. Solo se permiten imágenes JPG, PNG o WEBP.");
            return "redirect:/usuarios/" + id;
        }

        String extension = switch (contentType) {
            case "image/jpeg" -> ".jpg";
            case "image/png" -> ".png";
            case "image/webp" -> ".webp";
            default -> "";
        };

        String nombreArchivo = id + extension;
        Path carpeta = Paths.get("src/main/resources/static/img/users/");

        try {
            // Eliminar archivos anteriores con otras extensiones
            Files.walk(carpeta)
                    .filter(p -> p.getFileName().toString().matches(id + "\\.(jpg|png|webp)"))
                    .forEach(p -> {
                        try {
                            Files.delete(p);
                        } catch (IOException ignored) {}
                    });

            Path rutaFinal = carpeta.resolve(nombreArchivo);
            Files.write(rutaFinal, archivo.getBytes());
            redirectAttributes.addFlashAttribute("mensaje", "Foto de perfil actualizada correctamente.");

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar la imagen.");
        }

        return "redirect:/usuarios/" + id;
    }


    @GetMapping("/usuarios/{id}/foto")
    public ResponseEntity<Resource> obtenerFotoPerfil(@PathVariable Long id) throws IOException {
        String[] extensiones = {".png", ".jpg", ".webp"};
        Path folder = Paths.get("src/main/resources/static/img/users/");

        for (String ext : extensiones) {
            Path archivo = folder.resolve(id + ext);
            if (Files.exists(archivo)) {
                Resource resource = new UrlResource(archivo.toUri());
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(archivo))
                        .body(resource);
            }
        }

        // Si no existe imagen, devuelve la default
        Path defaultImage = Paths.get("src/main/resources/static/img/user.png");
        Resource resource = new UrlResource(defaultImage.toUri());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(defaultImage))
                .body(resource);
    }




}
