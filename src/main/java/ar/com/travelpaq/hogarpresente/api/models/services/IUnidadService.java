package ar.com.travelpaq.hogarpresente.api.models.services;

import ar.com.travelpaq.hogarpresente.api.models.domain.Alumno;
import ar.com.travelpaq.hogarpresente.api.models.domain.Unidad;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUnidadService {
    public List<Unidad> findAll();

    public ResponseEntity<?> findById(String nombre);

    public ResponseEntity<?> create(Unidad unidad);

    public ResponseEntity<?> update(Unidad unidad, String nombre);

    public ResponseEntity<?> delete(String nombre);
}
