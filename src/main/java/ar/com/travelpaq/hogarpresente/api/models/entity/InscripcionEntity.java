package ar.com.travelpaq.hogarpresente.api.models.entity;
import ar.com.travelpaq.hogarpresente.api.security.entity.UsuarioEntity;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuarioInscripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private CursoEntity cursoInscripcion;

    @PrePersist
    public void prePersist(){
        inscripcionAt = new Date();
    }

}
