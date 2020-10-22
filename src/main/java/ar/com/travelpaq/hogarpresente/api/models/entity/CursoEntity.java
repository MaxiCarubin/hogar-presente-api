package ar.com.travelpaq.hogarpresente.api.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Cursos")
public class CursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    private String capacitador;

    private int cantidad_unidades;

    private double precio;

    private float horas;

}
