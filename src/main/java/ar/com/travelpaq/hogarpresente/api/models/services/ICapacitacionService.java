package ar.com.travelpaq.hogarpresente.api.models.services;

import org.springframework.http.ResponseEntity;

public interface ICapacitacionService {
    public ResponseEntity<?> findAll();

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> delete(Long id);
}
