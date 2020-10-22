package ar.com.travelpaq.hogarpresente.api.models.repository;

import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import org.springframework.data.repository.CrudRepository;

public interface IAlumnoRepository extends CrudRepository<AlumnoEntity, Long> {
}
