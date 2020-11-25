package ar.com.travelpaq.hogarpresente.api.models.services;

import ar.com.travelpaq.hogarpresente.api.models.dto.AlumnoDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IAlumnoService {

    Optional<AlumnoEntity> getByCorreo(String correo);

    public boolean existsByCorreo(String correo);

    public void save(AlumnoEntity alumnoEntity);

    public ResponseEntity<?> findAll();

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> create(AlumnoDto alumnoDto);

    public ResponseEntity<?> update(AlumnoDto alumnoDto, Long id);

    public ResponseEntity<?> delete(Long id);

}
