package ar.com.travelpaq.hogarpresente.api.models.domain;
import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inscripcion {

    private Date inscripcionAt;

    private AlumnoEntity alumnoEntity;

    private CursoEntity cursoEntity;

}
