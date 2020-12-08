package ar.com.travelpaq.hogarpresente.api.models.entity;

import ar.com.travelpaq.hogarpresente.api.security.entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cursos")
public class CursoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 45)
    private String titulo;

    @Column(nullable = false, length = 45)
    private String subtitulo;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @PositiveOrZero
    private float precio;

    @Column(nullable = false, length = 45)
    private String categoria;

    @Column(length = 400)
    private String imagen;

    private boolean habilitado;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @OneToMany(targetEntity = UnidadEntity.class,cascade = CascadeType.ALL)
    private List<UnidadEntity> unidades;

}
