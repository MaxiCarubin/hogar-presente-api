package ar.com.travelpaq.hogarpresente.api.models.services;

import ar.com.travelpaq.hogarpresente.api.models.domain.Alumno;
import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;

import java.util.List;

public interface IAlumnoService {

    public List<Alumno> findAll();

    public Alumno findById(Long id);

    public Alumno create(Alumno alumno);

    public Alumno update(Alumno alumno, Long id);

    public void delete(Long id);

    AlumnoEntity findByCorreo(String correo);
}
