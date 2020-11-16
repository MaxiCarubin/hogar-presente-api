package ar.com.travelpaq.hogarpresente.api.models.entity;

import ar.com.travelpaq.hogarpresente.api.models.dto.CursoDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.UnidadDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
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

    //@Temporal(TemporalType.TIME)
    private Time horas;

    private Boolean terminado;

    private float progreso;

    private Boolean enabled;
    /*
    @OneToMany(fetch = FetchType.LAZY, targetEntity = InscripcionEntity.class)
    private Set<InscripcionEntity> inscripciones;
    */
    @OneToMany(fetch = FetchType.LAZY, targetEntity = UnidadEntity.class, cascade = CascadeType.ALL)
    private List<UnidadEntity> unidades;

    public CursoDto convertToCurso(CursoEntity cursoEntity){
        CursoDto cursoDto = new CursoDto();
        cursoDto.setId(cursoEntity.getId());
        cursoDto.setNombre(cursoEntity.getNombre());
        cursoDto.setDescripcion(cursoEntity.getDescripcion());
        cursoDto.setCapacitador(cursoEntity.getCapacitador());
        cursoDto.setPrecio(cursoEntity.getPrecio());
        cursoDto.setHoras(cursoEntity.getHoras());
        cursoDto.setEnabled(cursoEntity.getEnabled());

        List<UnidadDto> unidaddesDominio = new ArrayList<>();
        List<UnidadEntity> unidadesEntity = unidades;
        unidadesEntity.forEach(unidadEntity -> unidaddesDominio.add(unidadEntity.convertToUnidad(unidadEntity)));
        cursoDto.setUnidades(unidaddesDominio);

        /*
        Set<Inscripcion> inscripcioesDominio =  new HashSet<>();
        Set<InscripcionEntity> inscripcionesEntity = inscripciones;
        inscripcionesEntity.forEach(inscripcionEntity -> inscripcioesDominio.add(inscripcionEntity.convertToInscripcion(inscripcionEntity)));
        curso.setInscripciones(inscripcioesDominio);
        */

        return cursoDto;
    }

}
