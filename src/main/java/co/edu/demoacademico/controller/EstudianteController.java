package co.edu.demoacademico.controller;

import co.edu.demoacademico.model.Estudiante;
import co.edu.demoacademico.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// Capa de presentación. Recibe peticiones HTTP y delega al servicio.
@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService service;

    public EstudianteController(EstudianteService service) {
        this.service = service;
    }

    // POST /api/estudiantes — crea un estudiante, retorna 409 si el email ya existe
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Estudiante estudiante) {
        try {
            Estudiante creado = service.crear(estudiante);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (EstudianteService.EmailDuplicadoException ex) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("error", ex.getMessage()));
        }
    }

    // GET /api/estudiantes — lista todos los estudiantes
    @GetMapping
    public List<Estudiante> listar() {
        return service.listar();
    }

    // GET /api/estudiantes/buscar?email= — busca por email, retorna 404 si no existe
    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorEmail(@RequestParam String email) {
        return service.buscarPorEmail(email)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "No se encontró ningún estudiante con el email: " + email)));
    }
}
