package ar.com.travelpaq.hogarpresente.api.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContenidoDto {
    private long id;
    private String nombre;
    private String descripcion;
    private String documento;
    private long unidadId;
}
