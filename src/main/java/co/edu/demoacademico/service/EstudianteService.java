package co.edu.demoacademico.service;

import co.edu.demoacademico.model.Estudiante;
import co.edu.demoacademico.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Capa de lógica de negocio. No conoce HTTP ni detalles de persistencia.
@Service
public class EstudianteService {

    private final EstudianteRepository repository;

    public EstudianteService(EstudianteRepository repository) {
        this.repository = repository;
    }

    public Estudiante crear(Estudiante estudiante) {
        // Regla de negocio: el email debe ser único
        if (repository.findByEmail(estudiante.getEmail()).isPresent()) {
            throw new EmailDuplicadoException("El email '" + estudiante.getEmail() + "' ya está registrado.");
        }
        return repository.save(estudiante);
    }

    public List<Estudiante> listar() {
        return repository.findAll();
    }

    public Optional<Estudiante> buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    // Excepción de negocio para email duplicado, permite retornar HTTP 409 al cliente
    public static class EmailDuplicadoException extends RuntimeException {
        public EmailDuplicadoException(String message) {
            super(message);
        }
    }
}
