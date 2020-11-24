package ar.com.travelpaq.hogarpresente.api.models.services;
import ar.com.travelpaq.hogarpresente.api.models.dto.CursoDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICursoService {

    public ResponseEntity<?> findAll();

    public boolean findById2(Long id);

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> create(CursoDto cursoDto);

    public ResponseEntity<?> update(CursoDto cursoDto, Long id);

    public ResponseEntity<?> delete(Long id);
}
