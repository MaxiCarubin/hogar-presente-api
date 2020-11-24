package ar.com.travelpaq.hogarpresente.api.models.services;

import ar.com.travelpaq.hogarpresente.api.models.dto.TareaDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITareaService {
    public ResponseEntity<List<TareaDto>> findAll();

    public ResponseEntity<?> findById(Long id);

    public  ResponseEntity<?> create(TareaDto tareaDto);

    public  ResponseEntity<?> update(TareaDto tareaDto, Long id);

    public  ResponseEntity<?> delete(Long id);

//    public ResponseEntity<?> findByUnidadId(Long id);
}
