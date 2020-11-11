package ar.com.travelpaq.hogarpresente.api.models.dto;
import ar.com.travelpaq.hogarpresente.api.models.entity.InscripcionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscripcionDto {

    private long id;

    private Date inscripcionAt;

    private AlumnoDto alumnoDto;

    private CursoDto cursoDto;

    public InscripcionEntity convertToInscripcionEntity(InscripcionDto inscripcionDto) {
        InscripcionEntity inscripcionEntity = new InscripcionEntity();
        inscripcionEntity.setId(inscripcionDto.getId());
        inscripcionEntity.setInscripcionAt(inscripcionDto.getInscripcionAt());
        inscripcionEntity.setAlumnoEntity(inscripcionDto.getAlumnoDto().convertToAlumnoEntity(inscripcionDto.getAlumnoDto()));
        inscripcionEntity.setCursoEntity(inscripcionDto.getCursoDto().convertToCursoEntity(inscripcionDto.getCursoDto()));
        return inscripcionEntity;
    }
}
