package ar.com.travelpaq.hogarpresente.api.models.entity;

import ar.com.travelpaq.hogarpresente.api.models.domain.Alumno;
import ar.com.travelpaq.hogarpresente.api.models.domain.Inscripcion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inscripciones")
public class InscripcionEntity {
    @Id
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

    public Inscripcion convertToInscripcion() {
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setInscripcionAt(inscripcionAt);
        inscripcion.setAlumno(alumnoEntity.convertToAlumno());
        inscripcion.setCurso(cursoEntity.convertToCurso());
        return inscripcion;
    }
}
