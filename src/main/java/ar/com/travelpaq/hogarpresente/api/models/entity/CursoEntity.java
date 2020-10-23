package ar.com.travelpaq.hogarpresente.api.models.entity;

import ar.com.travelpaq.hogarpresente.api.models.domain.Curso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    private double precio;
    private float horas;
    @OneToMany(targetEntity = InscripcionEntity.class)
    private List inscripciones;
    @OneToMany(targetEntity = UnidadEntity.class)
    private List unidades;

    public Curso convertToCurso(){
        Curso curso = new Curso();
        curso.setId(id);
        curso.setNombre(nombre);
        curso.setDescripcion(descripcion);
        curso.setCapacitador(capacitador);
        curso.setPrecio(precio);
        curso.setHoras(horas);
        curso.setUnidades(unidades);
        curso.setInscripciones(inscripciones);

        return curso;
    }

}
