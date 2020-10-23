package ar.com.travelpaq.hogarpresente.api.models.services;

import ar.com.travelpaq.hogarpresente.api.models.domain.Alumno;
import ar.com.travelpaq.hogarpresente.api.models.domain.Curso;
import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;

import java.util.List;

public interface ICursoService {

    public List<Curso> findAll();

    public Curso findById(long id);

    public Curso create(Curso curso);

    public Curso update(Curso curso, long id);

    public void delete(long id);
}
