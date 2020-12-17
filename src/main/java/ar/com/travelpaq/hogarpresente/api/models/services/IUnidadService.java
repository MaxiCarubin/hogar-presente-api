package ar.com.travelpaq.hogarpresente.api.models.services;

import ar.com.travelpaq.hogarpresente.api.models.dto.UnidadDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUnidadService {
    public ResponseEntity<List<UnidadDto>> findAll();

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> create(UnidadDto unidadDto);

    public ResponseEntity<?> update(UnidadDto unidadDto, Long id);

    public ResponseEntity<?> delete(Long id);

    public ResponseEntity<?> createNull(long cursoId);

    public ResponseEntity<?> updateDescripcion(String descripcion, Long id);

    public ResponseEntity<?> updateNombre(String nombre, Long id);

//    public ResponseEntity<?> findByCursoId(Long id);
}
