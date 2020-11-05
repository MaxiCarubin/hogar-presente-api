package ar.com.travelpaq.hogarpresente.api.models.services;

import ar.com.travelpaq.hogarpresente.api.models.domain.Inscripcion;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface IInscripcionService {
    public List<Inscripcion> findAll();

    public ResponseEntity<?> create(Inscripcion inscripcion);

    public ResponseEntity<?> delete(Date fecha);
}
