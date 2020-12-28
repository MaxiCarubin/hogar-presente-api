package ar.com.travelpaq.hogarpresente.api.models.dto;

import ar.com.travelpaq.hogarpresente.api.models.entity.EvaluacionRendidaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MostrarEvaluacionDto {
    private long id;
    private String nombre;
    private String urlCapacitador;
    private List<EvaluacionRendidaEntity> evaluacionesRendidas;
    private long unidadId;
}
