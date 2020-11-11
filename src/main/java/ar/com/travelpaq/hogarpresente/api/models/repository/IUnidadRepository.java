package ar.com.travelpaq.hogarpresente.api.models.repository;

import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUnidadRepository extends JpaRepository<UnidadEntity, Long> {
}
