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
@Table(name = "capacitaciones")
public class CapacitacionEntity {
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

    @OneToMany(mappedBy = "capacitacion", cascade = CascadeType.ALL)
    private List<CursoEntity> cursos;
}
