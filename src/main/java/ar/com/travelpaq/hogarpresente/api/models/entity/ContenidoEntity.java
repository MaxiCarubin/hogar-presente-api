package ar.com.travelpaq.hogarpresente.api.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contenidos")
public class ContenidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 60)
    private String nombre;

    @Column(nullable = false, length = 70)
    private String descripcion;

    @Column(nullable = false, length = 60)
    private String documento;

//    @ManyToOne
//    @JoinColumn(name = "unidad_id")
//    private UnidadEntity unidad;

//    private Boolean terminada;

}
