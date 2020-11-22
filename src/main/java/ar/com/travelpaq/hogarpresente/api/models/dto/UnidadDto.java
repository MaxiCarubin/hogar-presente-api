package ar.com.travelpaq.hogarpresente.api.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnidadDto {
    private long id;

    private String nombre;

    private String descripcion;

//    private List<TareaDto> tareas;

//    private Boolean terminada;

    private long cursoId;

}
