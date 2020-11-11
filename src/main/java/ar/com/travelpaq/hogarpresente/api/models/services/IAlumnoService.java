package ar.com.travelpaq.hogarpresente.api.models.services;

import ar.com.travelpaq.hogarpresente.api.models.dto.AlumnoDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAlumnoService {

    public ResponseEntity<List<AlumnoEntity>> findAll();

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> create(AlumnoDto alumnoDto);

    public ResponseEntity<?> update(AlumnoDto alumnoDto, Long id);

    public ResponseEntity<?> delete(Long id);

    AlumnoEntity findByCorreo(String correo);
}
