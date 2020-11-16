package ar.com.travelpaq.hogarpresente.api.models.dto;
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
public class UnidadDto {
    private long id;

    private String nombre;

    private String descripcion;

    private List<TareaDto> tareaDtos;

    private Boolean terminada;

    public UnidadEntity convertToUnidadEntity(UnidadDto unidadDto) {
        UnidadEntity unidadEntity = new UnidadEntity();
        unidadEntity.setId(unidadDto.getId());
        unidadEntity.setNombre(unidadDto.getNombre());
        unidadEntity.setDescripcion(unidadDto.getDescripcion());

        List<TareaEntity> tareasEntity = new ArrayList<>();

        List<TareaDto> tareasDominio = tareaDtos;

        tareasDominio.forEach(tarea -> tareasEntity.add(tarea.convertToTareaEntity(tarea)));

        unidadEntity.setTareas(tareasEntity);

        return unidadEntity;
    }
}
