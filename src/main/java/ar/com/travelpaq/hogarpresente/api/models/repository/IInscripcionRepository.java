package ar.com.travelpaq.hogarpresente.api.models.repository;

import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.InscripcionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IInscripcionRepository extends JpaRepository<InscripcionEntity, Long> {
    List<InscripcionEntity> findAllByAlumno(AlumnoEntity alumnoEntity);

    List<InscripcionEntity> findAllByCurso(CursoEntity cursoEntity);
}
