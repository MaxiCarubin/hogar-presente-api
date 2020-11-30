package ar.com.travelpaq.hogarpresente.api.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cursos")
public class CursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 45)
    private String nombre;

    @Column(nullable = false, length = 70)
    private String descripcion;

    @Column(nullable = false, length = 45)
    private String capacitador;

    private float precio;

    private String categoria;

    private String imagen;

    private boolean habilitado;

    @OneToMany(targetEntity = UnidadEntity.class,cascade = CascadeType.ALL)
    private List<UnidadEntity> unidades;

}
