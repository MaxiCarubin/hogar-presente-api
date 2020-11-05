package ar.com.travelpaq.hogarpresente.api.models.services;

import ar.com.travelpaq.hogarpresente.api.models.domain.Alumno;
import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAlumnoService {

    public List<Alumno> findAll();

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> create(Alumno alumno);

    public ResponseEntity<?> update(Alumno alumno, Long id);

    public ResponseEntity<?> delete(Long id);

    AlumnoEntity findByCorreo(String correo);
}
