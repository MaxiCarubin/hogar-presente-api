package ar.com.travelpaq.hogarpresente.api.models.services;

import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;

import java.util.List;

public interface ICursoService {

    public List<CursoEntity> findAll();

    public CursoEntity findById(long id);

    public CursoEntity save(CursoEntity cursoEntity);

    public void delete(long id);
}
