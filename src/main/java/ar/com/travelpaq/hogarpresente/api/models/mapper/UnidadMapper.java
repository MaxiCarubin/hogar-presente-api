package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.dto.TareaDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.UnidadDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.TareaEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UnidadMapper {
    public UnidadDto mapUnidadEntityToUnidad(UnidadEntity unidadEntity){
        UnidadDto unidadDto = new UnidadDto();
        unidadDto.setId(unidadEntity.getId());
        unidadDto.setNombre(unidadEntity.getNombre());
        unidadDto.setDescripcion(unidadEntity.getDescripcion());

        List<TareaDto> tareaDtos = new ArrayList<>();
        unidadEntity.getTareas().forEach(tareaEntity -> tareaDtos.add(tareaEntity.convertToTarea(tareaEntity)));
        unidadDto.setTareaDtos(tareaDtos);

        return unidadDto;
    }
    public UnidadEntity mapUnidadToUnidadEntity(UnidadDto unidadDto){
        UnidadEntity unidadEntity = new UnidadEntity();
        unidadEntity.setId(unidadDto.getId());
        unidadEntity.setNombre(unidadDto.getNombre());
        unidadEntity.setDescripcion(unidadDto.getDescripcion());

        List<TareaEntity> tareaEntities = new ArrayList<>();
        unidadDto.getTareaDtos().forEach(tarea -> tareaEntities.add(tarea.convertToTareaEntity(tarea)));
        unidadEntity.setTareas(tareaEntities);

        return unidadEntity;
    }
}
