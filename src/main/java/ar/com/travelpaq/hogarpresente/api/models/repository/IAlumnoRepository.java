package ar.com.travelpaq.hogarpresente.api.models.repository;

import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAlumnoRepository extends JpaRepository<AlumnoEntity, Long> {
    AlumnoEntity findByCorreo(String correo);
}
