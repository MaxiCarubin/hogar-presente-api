package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.dto.TareaDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.TareaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TareaMapper {

    @Autowired
    private UnidadMapper unidadMapper;

    public TareaDto mapTareaToTareaEntity(TareaEntity tareaEntity) {
        TareaDto tareaDto = new TareaDto();
        tareaDto.setId(tareaEntity.getId());
        tareaDto.setNombre(tareaEntity.getNombre());
        tareaDto.setDescripcion(tareaEntity.getDescripcion());
        tareaDto.setDocumento(tareaEntity.getDocumento());
//        tareaDto.setUnidad(unidadMapper.mapUnidadEntityToUnidad(tareaEntity.getUnidad()));
        return tareaDto;
    }
    public TareaEntity mapTareaEntityToTarea(TareaDto tareaDto) {
        TareaEntity tareaEntity = new TareaEntity();
        tareaEntity.setId(tareaDto.getId());
        tareaEntity.setNombre(tareaDto.getNombre());
        tareaEntity.setDescripcion(tareaDto.getDescripcion());
        tareaEntity.setDocumento(tareaDto.getDocumento());
//        tareaEntity.setUnidad(unidadMapper.mapUnidadToUnidadEntity(tareaDto.getUnidad()));
        return tareaEntity;
    }
}
