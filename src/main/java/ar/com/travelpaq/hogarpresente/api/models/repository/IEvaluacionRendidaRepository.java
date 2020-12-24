package ar.com.travelpaq.hogarpresente.api.models.repository;

import ar.com.travelpaq.hogarpresente.api.models.entity.EvaluacionRendidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEvaluacionRendidaRepository extends JpaRepository<EvaluacionRendidaEntity, Long> {
}
