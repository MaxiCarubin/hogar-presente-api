package ar.com.travelpaq.hogarpresente.api.models.repository;

import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import org.springframework.data.repository.CrudRepository;

public interface ICursoRepository extends CrudRepository<CursoEntity, Long> {
}
