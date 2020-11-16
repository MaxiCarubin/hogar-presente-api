package ar.com.travelpaq.hogarpresente.api.models.dto;

import ar.com.travelpaq.hogarpresente.api.models.entity.TareaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TareaDto {
    private long id;
    private String nombre;
    private String descripcion;
    private String documento;
    private Boolean terminada;

    public TareaEntity convertToTareaEntity(TareaDto tareaDto) {
        TareaEntity tareaEntity = new TareaEntity();
        tareaEntity.setId(tareaDto.getId());
        tareaEntity.setNombre(tareaDto.getNombre());
        tareaEntity.setDocumento(tareaDto.getDocumento());
        tareaEntity.setDescripcion(tareaDto.getDescripcion());
        return tareaEntity;
    }
}
