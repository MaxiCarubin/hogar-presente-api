package ar.com.travelpaq.hogarpresente.api.models.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inscripcion {

    private Date inscripcionAt;

    private Alumno alumno;

    private Curso curso;

}
