package ar.com.travelpaq.hogarpresente.api.models.services;

import ar.com.travelpaq.hogarpresente.api.models.dto.ContenidoDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.NuevoContenidoDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IContenidoService {
    public ResponseEntity<List<ContenidoDto>> findAll();

    public ResponseEntity<?> findById(Long id);

    public  ResponseEntity<?> create(ContenidoDto contenidoDto);

    public  ResponseEntity<?> update(ContenidoDto contenidoDto, Long id);

    public  ResponseEntity<?> delete(Long id);

    public ResponseEntity<?> createNull(NuevoContenidoDto contenidoDto);

    public ResponseEntity<?> updateNombre(String nombre, Long id);

    public ResponseEntity<?> updateDescripcion(String descripcion, Long id);

    public ResponseEntity<?> terminarOnOffCurso(Long id);


//    public ResponseEntity<?> findByUnidadId(Long id);
}
