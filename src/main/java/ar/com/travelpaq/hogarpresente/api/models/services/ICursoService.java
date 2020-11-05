package ar.com.travelpaq.hogarpresente.api.models.services;
import ar.com.travelpaq.hogarpresente.api.models.domain.Curso;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICursoService {

    public List<Curso> findAll();

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> create(Curso curso);

    public ResponseEntity<?> update(Curso curso, Long id);

    public ResponseEntity<?> delete(Long id);
}
