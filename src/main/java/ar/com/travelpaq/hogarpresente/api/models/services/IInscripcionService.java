package ar.com.travelpaq.hogarpresente.api.models.services;

import ar.com.travelpaq.hogarpresente.api.models.domain.Curso;
import ar.com.travelpaq.hogarpresente.api.models.domain.Inscripcion;

import java.util.Date;
import java.util.List;

public interface IInscripcionService {
    public List<Inscripcion> findAll();

    public Inscripcion create(Inscripcion inscripcion);

    public void delete(Date fecha);
}
