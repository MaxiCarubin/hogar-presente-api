package ar.com.travelpaq.hogarpresente.api.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
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
    private String nombre;

    @Column(nullable = false, length = 70)
    private String descripcion;

//    @Column(nullable = false, length = 45)
//    private String capacitador;

    private float precio;

    private String categoria;

    private String imagen;

    private boolean habilitado;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @OneToMany(targetEntity = UnidadEntity.class,cascade = CascadeType.ALL)
    private List<UnidadEntity> unidades;

}
