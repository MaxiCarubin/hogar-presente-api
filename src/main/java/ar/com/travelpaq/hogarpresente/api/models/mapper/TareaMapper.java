package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.domain.Tarea;
import ar.com.travelpaq.hogarpresente.api.models.entity.TareaEntity;
import org.springframework.stereotype.Component;

@Component
public class TareaMapper {
    public Tarea mapTareaToTareaEntity(TareaEntity tareaEntity) {
        Tarea tarea = new Tarea();
        tarea.setId(tareaEntity.getId());
        tarea.setNombre(tareaEntity.getNombre());
        tarea.setDescripcion(tareaEntity.getDescripcion());
        tarea.setDocumento(tareaEntity.getDocumento());

        return tarea;
    }
    public TareaEntity mapTareaEntityToTarea(Tarea tarea) {
        TareaEntity tareaEntity = new TareaEntity();
        tareaEntity.setId(tarea.getId());
        tareaEntity.setNombre(tarea.getNombre());
        tareaEntity.setDescripcion(tarea.getDescripcion());
        tareaEntity.setDocumento(tarea.getDocumento());

        return tareaEntity;
    }
}
