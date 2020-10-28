package ar.com.travelpaq.hogarpresente.api.models.entity;

import ar.com.travelpaq.hogarpresente.api.models.domain.Curso;
import ar.com.travelpaq.hogarpresente.api.models.domain.Inscripcion;
import ar.com.travelpaq.hogarpresente.api.models.domain.Unidad;
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
    @OneToMany( targetEntity = InscripcionEntity.class)
    private List<InscripcionEntity> inscripciones;
    @OneToMany( targetEntity = UnidadEntity.class)
    private List<UnidadEntity> unidades;

    public Curso convertToCurso(CursoEntity cursoEntity){
        Curso curso = new Curso();
        curso.setId(cursoEntity.getId());
        curso.setNombre(cursoEntity.getNombre());
        curso.setDescripcion(cursoEntity.getDescripcion());
        curso.setCapacitador(cursoEntity.getCapacitador());
        curso.setPrecio(cursoEntity.getPrecio());
        curso.setHoras(cursoEntity.getHoras());

        List<Unidad> unidaddesDominio = new ArrayList<>();
        List<UnidadEntity> unidadesEntity = unidades;
        unidadesEntity.forEach(unidadEntity -> unidaddesDominio.add(unidadEntity.convertToUnidad(unidadEntity)));

        curso.setUnidades(unidaddesDominio);

        List<Inscripcion> inscripcioesDominio = new ArrayList<>();
        List<InscripcionEntity> inscripcionesEntity = inscripciones;
        inscripcionesEntity.forEach(inscripcionEntity -> inscripcioesDominio.add(inscripcionEntity.convertToInscripcion(inscripcionEntity)));

        curso.setInscripciones(inscripcioesDominio);

        return curso;
    }

}
