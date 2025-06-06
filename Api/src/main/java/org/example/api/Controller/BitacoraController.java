package org.example.api.Controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.example.api.Entities.dtos.BitacoraCrearDTO;
import org.example.api.Entities.dtos.BitacoraDTO;
import org.example.api.Service.BitacoraService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bitacora")
public class BitacoraController {

    private final BitacoraService bitacoraService;

    public BitacoraController(BitacoraService bitacoraService) {
        this.bitacoraService = bitacoraService;
    }

    @Operation(summary = "Agregar una nueva entrada a la bitácora de juego")
    @PostMapping("/add")
    public ResponseEntity<?> agregarEntrada(@RequestBody BitacoraCrearDTO dto) {
        try {
            bitacoraService.agregarEntrada(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Entrada añadida correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al agregar la entrada.");
        }
    }
    @Operation(summary = "Obtener entradas de bitácora de un usuario con paginación")
    @GetMapping("/usuario/{id}/paginas")
    public ResponseEntity<Page<BitacoraDTO>> obtenerBitacoraPaginada(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Page<BitacoraDTO> bitacoraPage = bitacoraService.obtenerBitacoraPaginadaDeUsuario(id, page, size);
        return ResponseEntity.ok(bitacoraPage);
    }
    @Operation(summary = "Eliminar una entrada de bitácora por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEntrada(@PathVariable Long id) {
        try {
            bitacoraService.eliminarEntradaPorId(id);
            return ResponseEntity.ok("Entrada eliminada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la entrada.");
        }
    }

}

