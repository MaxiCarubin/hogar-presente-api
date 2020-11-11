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
    private long id;

    private String nombre;

    private String descripcion;

    private List<Tarea> tareas;

    public UnidadEntity convertToUnidadEntity(Unidad unidad) {
        UnidadEntity unidadEntity = new UnidadEntity();
        unidadEntity.setId(unidad.getId());
        unidadEntity.setNombre(unidad.getNombre());
        unidadEntity.setDescripcion(unidad.getDescripcion());

        List<TareaEntity> tareasEntity = new ArrayList<>();

        List<Tarea> tareasDominio = tareas;

        tareasDominio.forEach(tarea -> tareasEntity.add(tarea.convertToTareaEntity(tarea)));

        unidadEntity.setTareas(tareasEntity);

        return unidadEntity;
    }
}
