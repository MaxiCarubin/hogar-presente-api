package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.domain.Unidad;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import org.springframework.stereotype.Component;

@Component
public class UnidadMapper {
    public Unidad mapUnidadEntityToUnidad(UnidadEntity unidadEntity){
        Unidad unidad = new Unidad();
        unidad.setNombre(unidadEntity.getNombre());
        unidad.setDescripcion(unidadEntity.getDescripcion());
        unidad.setTareas(unidadEntity.getTareas());

        return unidad;
    }
    public UnidadEntity mapUnidadToUnidadEntity(Unidad unidad){
        UnidadEntity unidadEntity = new UnidadEntity();
        unidadEntity.setNombre(unidad.getNombre());
        unidadEntity.setDescripcion(unidad.getDescripcion());
        unidadEntity.setTareas(unidad.getTareas());

        return unidadEntity;
    }
}
