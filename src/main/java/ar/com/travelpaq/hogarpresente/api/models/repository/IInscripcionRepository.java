package ar.com.travelpaq.hogarpresente.api.models.repository;

import ar.com.travelpaq.hogarpresente.api.models.entity.InscripcionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;

public interface IInscripcionRepository extends JpaRepository<InscripcionEntity, Date> {
}
