package ar.com.travelpaq.hogarpresente.api.models.services;

import ar.com.travelpaq.hogarpresente.api.models.dto.InscripcionDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IInscripcionService {
    public ResponseEntity<List<InscripcionDto>> findAll();

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> create(InscripcionDto inscripcionDto);

    public ResponseEntity<?> update(InscripcionDto inscripcionDto, Long id);

    public ResponseEntity<?> delete(Long id);
}
