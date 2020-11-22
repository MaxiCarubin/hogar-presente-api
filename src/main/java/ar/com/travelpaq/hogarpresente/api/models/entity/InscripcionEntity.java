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
    private AlumnoEntity alumno;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;

    @PrePersist
    public void prePersist(){
        inscripcionAt = new Date();
    }

}
