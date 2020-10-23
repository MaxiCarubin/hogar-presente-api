package ar.com.travelpaq.hogarpresente.api.models.services;

import ar.com.travelpaq.hogarpresente.api.models.domain.Alumno;
import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAlumnoService {

    public List<Alumno> findAll();

    public Alumno findById(long id);

    public Alumno create(Alumno alumno);

    public Alumno update(Alumno alumno, long id);

    public void delete(long id);

}
