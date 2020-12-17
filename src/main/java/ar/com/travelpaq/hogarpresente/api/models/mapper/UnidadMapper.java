package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.dto.UnidadDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnidadMapper {

    @Autowired
    private CursoMapper cursoMapper;

    @Autowired
    private ContenidoMapper contenidoMapper;

    public UnidadEntity mapUnidadToUnidadEntity(UnidadDto unidadDto){
        UnidadEntity unidadEntity = new UnidadEntity();
        unidadEntity.setId(unidadDto.getId());
        unidadEntity.setNombre(unidadDto.getNombre());
        unidadEntity.setDescripcion(unidadDto.getDescripcion());
        return unidadEntity;
    }
}
