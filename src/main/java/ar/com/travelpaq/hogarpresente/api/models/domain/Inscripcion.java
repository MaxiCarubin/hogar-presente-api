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

    public InscripcionEntity convertToInscripcionEntity(Inscripcion inscripcion) {
        InscripcionEntity inscripcionEntity = new InscripcionEntity();
        inscripcionEntity.setInscripcionAt(inscripcion.getInscripcionAt());
        inscripcionEntity.setAlumnoEntity(inscripcion.getAlumno().convertToAlumnoEntity(inscripcion.getAlumno()));
        inscripcionEntity.setCursoEntity(inscripcion.getCurso().convertToCursoEntity(inscripcion.getCurso()));
        return inscripcionEntity;
    }
}
