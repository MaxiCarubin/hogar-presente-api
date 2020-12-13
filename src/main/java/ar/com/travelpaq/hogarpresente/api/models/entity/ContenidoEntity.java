package ar.com.travelpaq.hogarpresente.api.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(nullable = false, length = 45)
    private String titulo;

    @Column(nullable = false, length = 45)
    private String subtitulo;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column(nullable = false, length = 500)
    private String documento;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidad_id")
    private UnidadEntity unidad;

}
