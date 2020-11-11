package ar.com.travelpaq.hogarpresente.api.models.services;

import ar.com.travelpaq.hogarpresente.api.models.domain.Unidad;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUnidadService {
    public List<Unidad> findAll();

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> create(Unidad unidad);

    public ResponseEntity<?> update(Unidad unidad, Long id);

    public ResponseEntity<?> delete(Long id);
}
