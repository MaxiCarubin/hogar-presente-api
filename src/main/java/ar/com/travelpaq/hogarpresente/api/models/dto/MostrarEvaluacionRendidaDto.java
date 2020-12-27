package ar.com.travelpaq.hogarpresente.api.models.dto;

import ar.com.travelpaq.hogarpresente.api.models.enums.Resultado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MostrarEvaluacionRendidaDto {
    private long id;
    private String urlAlumno;
    private Resultado resultado;
    private Integer nota;
    private long alumnoId;
    private long evaluacionId;
}
