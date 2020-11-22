package ar.com.travelpaq.hogarpresente.api.models.entity;

import ar.com.travelpaq.hogarpresente.api.models.dto.TareaDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.UnidadDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
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

    @Column(nullable = false, length = 75)
    private String nombre;

    @Column(nullable = false, length = 75)
    private String descripcion;

//    private Boolean terminada;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;

//    @OneToMany(targetEntity = TareaEntity.class, cascade = CascadeType.ALL)
//    private List<TareaEntity> tareas;

}
