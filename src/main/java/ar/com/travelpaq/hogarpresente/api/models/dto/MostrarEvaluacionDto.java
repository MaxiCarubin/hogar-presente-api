package ar.com.travelpaq.hogarpresente.api.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MostrarEvaluacionDto {
    private long id;
    private String nombre;
    private String urlCapacitador;
    private long unidadId;
}
