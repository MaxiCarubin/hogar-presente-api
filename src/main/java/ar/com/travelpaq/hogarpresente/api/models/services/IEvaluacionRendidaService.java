package ar.com.travelpaq.hogarpresente.api.models.services;

import ar.com.travelpaq.hogarpresente.api.models.dto.NuevaEvaluacionRendidaDto;
import org.springframework.http.ResponseEntity;

public interface IEvaluacionRendidaService {
    public ResponseEntity<?> findAll();

    public ResponseEntity<?> create(NuevaEvaluacionRendidaDto evaluacion);

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> delete(Long id);

    public ResponseEntity<?> updateCorreccion(Integer nota, Long id);
}
