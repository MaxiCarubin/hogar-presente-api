package ar.com.travelpaq.hogarpresente.api.models.services;

import ar.com.travelpaq.hogarpresente.api.models.domain.Tarea;
import ar.com.travelpaq.hogarpresente.api.models.domain.Unidad;

import java.util.List;

public interface ITareaService {
    public List<Tarea> findAll();

    public Tarea findById(String nombre);

    public Tarea create(Tarea tarea);

    public Tarea update(Tarea tarea, String nombre);

    public void delete(String nombre);
}
