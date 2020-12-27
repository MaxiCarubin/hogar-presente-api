package ar.com.travelpaq.hogarpresente.api.models.repository;

import ar.com.travelpaq.hogarpresente.api.models.entity.EvaluacionEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.EvaluacionRendidaEntity;
import ar.com.travelpaq.hogarpresente.api.security.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEvaluacionRendidaRepository extends JpaRepository<EvaluacionRendidaEntity, Long> {
    List<EvaluacionRendidaEntity> findByOrderById();

    boolean existsByAlumno(UsuarioEntity alumnoEntityGet);

    boolean existsByEvaluacion(EvaluacionEntity evaluacionEntityGet);
}
