package ar.com.travelpaq.hogarpresente.api.models.entity;

import ar.com.travelpaq.hogarpresente.api.models.domain.Curso;
import ar.com.travelpaq.hogarpresente.api.models.domain.Inscripcion;
import ar.com.travelpaq.hogarpresente.api.models.domain.Unidad;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cursos")
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

    private Boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = InscripcionEntity.class)
    private Set<InscripcionEntity> inscripciones;
    @OneToMany(fetch = FetchType.LAZY, targetEntity = UnidadEntity.class, cascade = CascadeType.ALL)
    private List<UnidadEntity> unidades;

    public Curso convertToCurso(CursoEntity cursoEntity){
        Curso curso = new Curso();
        curso.setId(cursoEntity.getId());
        curso.setNombre(cursoEntity.getNombre());
        curso.setDescripcion(cursoEntity.getDescripcion());
        curso.setCapacitador(cursoEntity.getCapacitador());
        curso.setPrecio(cursoEntity.getPrecio());
        curso.setHoras(cursoEntity.getHoras());
        curso.setEnabled(cursoEntity.getEnabled());
        List<Unidad> unidaddesDominio = new ArrayList<>();
        List<UnidadEntity> unidadesEntity = unidades;
        unidadesEntity.forEach(unidadEntity -> unidaddesDominio.add(unidadEntity.convertToUnidad(unidadEntity)));

        curso.setUnidades(unidaddesDominio);

        Set<Inscripcion> inscripcioesDominio =  new HashSet<>();
        Set<InscripcionEntity> inscripcionesEntity = inscripciones;
        inscripcionesEntity.forEach(inscripcionEntity -> inscripcioesDominio.add(inscripcionEntity.convertToInscripcion(inscripcionEntity)));

        curso.setInscripciones(inscripcioesDominio);

        return curso;
    }

}
