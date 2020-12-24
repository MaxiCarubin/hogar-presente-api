package ar.com.travelpaq.hogarpresente.api.models.entity;

import ar.com.travelpaq.hogarpresente.api.security.entity.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "evaluaciones_rendidas")
public class EvaluacionRendidaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 500)
    private String urlAlumno;

    private boolean resultado;

    private int nota;

    private boolean terminado;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alumno_id")
    private UsuarioEntity alumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluacion_id")
    private EvaluacionEntity evaluacion;


}
