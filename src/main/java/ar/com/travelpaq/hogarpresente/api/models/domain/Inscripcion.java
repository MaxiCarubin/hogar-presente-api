package ar.com.travelpaq.hogarpresente.api.models.domain;
import ar.com.travelpaq.hogarpresente.api.models.entity.InscripcionEntity;
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

    public InscripcionEntity convertToInscripcionEntity() {
        InscripcionEntity inscripcionEntity = new InscripcionEntity();
        inscripcionEntity.setInscripcionAt(inscripcionAt);
        inscripcionEntity.setAlumnoEntity(alumno.convertToAlumnoEntity());
        inscripcionEntity.setCursoEntity(curso.convertToCursoEntity());
        return inscripcionEntity;
    }
}
