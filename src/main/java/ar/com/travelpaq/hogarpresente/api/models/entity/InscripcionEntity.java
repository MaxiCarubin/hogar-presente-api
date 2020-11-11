package ar.com.travelpaq.hogarpresente.api.models.entity;
import ar.com.travelpaq.hogarpresente.api.models.dto.InscripcionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inscripciones")
public class InscripcionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "inscripcion_at")
    @Temporal(TemporalType.DATE)
    private Date inscripcionAt;

    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private AlumnoEntity alumnoEntity;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoEntity cursoEntity;

    @PrePersist
    public void prePersist(){
        inscripcionAt = new Date();
    }

    public InscripcionDto convertToInscripcion(InscripcionEntity inscripcionEntity) {
        InscripcionDto inscripcionDto = new InscripcionDto();
        inscripcionDto.setId(inscripcionEntity.getId());
        inscripcionDto.setInscripcionAt(inscripcionEntity.getInscripcionAt());
        inscripcionDto.setAlumnoDto(inscripcionEntity.getAlumnoEntity().convertToAlumno(inscripcionEntity.getAlumnoEntity()));
        inscripcionDto.setCursoDto(inscripcionEntity.getCursoEntity().convertToCurso(inscripcionEntity.getCursoEntity()));
        return inscripcionDto;
    }
}
