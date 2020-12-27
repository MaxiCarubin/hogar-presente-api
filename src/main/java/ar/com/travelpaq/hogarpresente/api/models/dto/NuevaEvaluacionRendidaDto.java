package ar.com.travelpaq.hogarpresente.api.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NuevaEvaluacionRendidaDto {
    private String urlAlumno;
    private long alumnoId;
    private long evaluacionId;
}
