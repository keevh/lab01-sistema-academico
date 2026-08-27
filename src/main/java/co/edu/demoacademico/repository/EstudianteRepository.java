package co.edu.demoacademico.repository;

import co.edu.demoacademico.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Capa de acceso a datos. Spring Data JPA genera las implementaciones automáticamente.
@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    // Zona de acceso a BD: Spring Data genera SELECT ... WHERE email = ?
    Optional<Estudiante> findByEmail(String email);
}
