package ar.com.travelpaq.hogarpresente.api.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnidadDto {
    private long id;

    private String nombre;

    private String descripcion;

//    private Boolean terminada;

    private List<Long> tareasId;

    private long cursoId;

}
