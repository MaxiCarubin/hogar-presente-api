package ar.com.travelpaq.hogarpresente.api.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "unidades")
public class UnidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 45)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    private int numeroUnidad;
//    private Boolean terminada;
//    @ManyToOne
//    @JoinColumn(name = "curso_id")
//    private CursoEntity curso;

    @OneToMany(mappedBy = "unidad", cascade = CascadeType.ALL)
    private List<ContenidoEntity> contenidos;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;

    public UnidadEntity(long id, String nombre, String descripcion, List<ContenidoEntity> contenidos, CursoEntity curso) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.numeroUnidad = 0;
        this.contenidos = contenidos;
        this.curso = curso;
    }
}
