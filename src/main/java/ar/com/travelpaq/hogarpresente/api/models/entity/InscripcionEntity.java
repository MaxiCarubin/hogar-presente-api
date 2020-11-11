package ar.com.travelpaq.hogarpresente.api.models.entity;
import ar.com.travelpaq.hogarpresente.api.models.domain.Inscripcion;
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

    public Inscripcion convertToInscripcion(InscripcionEntity inscripcionEntity) {
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId(inscripcionEntity.getId());
        inscripcion.setInscripcionAt(inscripcionEntity.getInscripcionAt());
        inscripcion.setAlumno(inscripcionEntity.getAlumnoEntity().convertToAlumno(inscripcionEntity.getAlumnoEntity()));
        inscripcion.setCurso(inscripcionEntity.getCursoEntity().convertToCurso(inscripcionEntity.getCursoEntity()));
        return inscripcion;
    }
}
