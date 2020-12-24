package ar.com.travelpaq.hogarpresente.api.models.repository;

import ar.com.travelpaq.hogarpresente.api.models.entity.EvaluacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEvaluacionReposiroty extends JpaRepository<EvaluacionEntity, Long> {
    List<EvaluacionEntity> findByOrderById();
}
