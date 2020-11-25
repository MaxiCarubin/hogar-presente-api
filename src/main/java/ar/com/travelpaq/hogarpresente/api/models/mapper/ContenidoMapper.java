package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.dto.ContenidoDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.ContenidoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContenidoMapper {

    @Autowired
    private UnidadMapper unidadMapper;

    public ContenidoDto mapTareaToTareaEntity(ContenidoEntity contenidoEntity) {
        ContenidoDto contenidoDto = new ContenidoDto();
        contenidoDto.setId(contenidoEntity.getId());
        contenidoDto.setNombre(contenidoEntity.getNombre());
        contenidoDto.setDescripcion(contenidoEntity.getDescripcion());
        contenidoDto.setDocumento(contenidoEntity.getDocumento());
//        tareaDto.setUnidad(unidadMapper.mapUnidadEntityToUnidad(tareaEntity.getUnidad()));
        return contenidoDto;
    }
    public ContenidoEntity mapTareaEntityToTarea(ContenidoDto contenidoDto) {
        ContenidoEntity contenidoEntity = new ContenidoEntity();
        contenidoEntity.setId(contenidoDto.getId());
        contenidoEntity.setNombre(contenidoDto.getNombre());
        contenidoEntity.setDescripcion(contenidoDto.getDescripcion());
        contenidoEntity.setDocumento(contenidoDto.getDocumento());
//        tareaEntity.setUnidad(unidadMapper.mapUnidadToUnidadEntity(tareaDto.getUnidad()));
        return contenidoEntity;
    }
}
