package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.domain.Tarea;
import ar.com.travelpaq.hogarpresente.api.models.domain.Unidad;
import ar.com.travelpaq.hogarpresente.api.models.entity.TareaEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UnidadMapper {
    public Unidad mapUnidadEntityToUnidad(UnidadEntity unidadEntity){
        Unidad unidad = new Unidad();
        unidad.setId(unidadEntity.getId());
        unidad.setNombre(unidadEntity.getNombre());
        unidad.setDescripcion(unidadEntity.getDescripcion());

        List<Tarea> tareas = new ArrayList<>();
        unidadEntity.getTareas().forEach(tareaEntity -> tareas.add(tareaEntity.convertToTarea(tareaEntity)));
        unidad.setTareas(tareas);

        return unidad;
    }
    public UnidadEntity mapUnidadToUnidadEntity(Unidad unidad){
        UnidadEntity unidadEntity = new UnidadEntity();
        unidadEntity.setId(unidad.getId());
        unidadEntity.setNombre(unidad.getNombre());
        unidadEntity.setDescripcion(unidad.getDescripcion());

        List<TareaEntity> tareaEntities = new ArrayList<>();
        unidad.getTareas().forEach(tarea -> tareaEntities.add(tarea.convertToTareaEntity(tarea)));
        unidadEntity.setTareas(tareaEntities);

        return unidadEntity;
    }
}
