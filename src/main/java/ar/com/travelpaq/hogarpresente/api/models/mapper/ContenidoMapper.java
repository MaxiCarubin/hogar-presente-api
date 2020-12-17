package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.dto.ContenidoDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.ContenidoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContenidoMapper {

    @Autowired
    private UnidadMapper unidadMapper;

    public ContenidoEntity mapTareaEntityToTarea(ContenidoDto contenidoDto) {
        ContenidoEntity contenidoEntity = new ContenidoEntity();
        contenidoEntity.setId(contenidoDto.getId());
        contenidoEntity.setNombre(contenidoDto.getNombre());
        contenidoEntity.setDescripcion(contenidoDto.getDescripcion());
        contenidoEntity.setDocumento(contenidoDto.getDocumento());
        return contenidoEntity;
    }
}
