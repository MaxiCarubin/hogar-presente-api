package ar.com.travelpaq.hogarpresente.api.models.domain;
import ar.com.travelpaq.hogarpresente.api.models.entity.TareaEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Unidad {

    private String nombre;

    private String descripcion;

    private List<Tarea> tareas;

    public UnidadEntity convertToUnidadEntity() {
        UnidadEntity unidadEntity = new UnidadEntity();
        unidadEntity.setNombre(nombre);
        unidadEntity.setDescripcion(descripcion);

        List<Tarea> tareasDominio = new ArrayList<>();

        List<TareaEntity> tareasEntity = new ArrayList<>();

        tareas.forEach(tarea -> tareasDominio.add(tarea));

        tareasDominio.forEach(tarea -> tareasEntity.add(tarea.convertToTareaEntity()));

        unidadEntity.setTareas(tareasEntity);

        return unidadEntity;
    }
}
