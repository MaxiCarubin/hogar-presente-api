package ar.com.travelpaq.hogarpresente.api.models.services;

import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;

import java.util.List;

public interface IAlumnoService {

    public List<AlumnoEntity> findAll();

    public AlumnoEntity findById(long id);

    public AlumnoEntity save(AlumnoEntity alumnoEntity);

    public void delete(long id);
}
