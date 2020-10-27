package ar.com.travelpaq.hogarpresente.api.models.services;

import ar.com.travelpaq.hogarpresente.api.models.domain.Alumno;
import ar.com.travelpaq.hogarpresente.api.models.domain.Unidad;

import java.util.List;

public interface IUnidadService {
    public List<Unidad> findAll();

    public Unidad findById(String nombre);

    public Unidad create(Unidad unidad);

    public Unidad update(Unidad unidad, String nombre);

    public void delete(String nombre);
}
