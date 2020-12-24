package ar.com.travelpaq.hogarpresente.api.models.services;

import ar.com.travelpaq.hogarpresente.api.models.dto.NuevaEvaluacionDto;
import org.springframework.http.ResponseEntity;

public interface IEvaluacionService {
    public ResponseEntity<?> findAll();

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> delete(Long id);

    public ResponseEntity<?> create(NuevaEvaluacionDto evaluacion);

    public ResponseEntity<?> updateNombre(String nombre, Long id);

    public ResponseEntity<?> updateUrlCapacitador(String urlCapacitador, Long id);
}

