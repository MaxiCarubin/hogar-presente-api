package ar.com.travelpaq.hogarpresente.api.models.entity;

import ar.com.travelpaq.hogarpresente.api.cloudinary.entity.ImagenEntity;
import ar.com.travelpaq.hogarpresente.api.security.entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

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

    private float precio;

    @Column(nullable = false, length = 45)
    private String categoria;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "imagen_id")
    private ImagenEntity imagen;

    private boolean habilitado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "curso",cascade = CascadeType.ALL)
    private List<UnidadEntity> unidades;

    @OneToMany(mappedBy = "cursoInscripcion",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<InscripcionEntity> inscripciones;

}
