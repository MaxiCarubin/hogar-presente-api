package ar.com.travelpaq.hogarpresente.api.models.domain;

import ar.com.travelpaq.hogarpresente.api.models.entity.TareaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarea {
    private String nombre;
    private String descripcion;
    private String documento;

    public TareaEntity convertToTareaEntity() {
        TareaEntity tareaEntity = new TareaEntity();
        tareaEntity.setNombre(nombre);
        tareaEntity.setDocumento(documento);
        tareaEntity.setDescripcion(descripcion);
        return tareaEntity;
    }
}
