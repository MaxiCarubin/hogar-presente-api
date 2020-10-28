package ar.com.travelpaq.hogarpresente.api.models.domain;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.InscripcionEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {

    private long id;

    private String nombre;

    private String descripcion;

    private String capacitador;

    private double precio;

    private float horas;

    private Set<Inscripcion> inscripciones;

    private List<Unidad> unidades;

    public CursoEntity convertToCursoEntity(Curso curso){
        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setId(curso.getId());
        cursoEntity.setNombre(curso.getNombre());
        cursoEntity.setDescripcion(curso.getDescripcion());
        cursoEntity.setPrecio(curso.getPrecio());
        cursoEntity.setCapacitador(curso.getCapacitador());
        cursoEntity.setHoras(curso.getHoras());

        Set<InscripcionEntity> inscripcionesEntity = new HashSet<>();

        Set<Inscripcion> inscripcioesDominio = inscripciones;

        inscripcioesDominio.forEach(inscripcion -> inscripcionesEntity.add(inscripcion.convertToInscripcionEntity(inscripcion)));

        cursoEntity.setInscripciones(inscripcionesEntity);

        List<UnidadEntity> unidadesEntity = new ArrayList<>();

        List<Unidad> unidadesDominio = unidades;

        unidadesDominio.forEach(unidad -> unidadesEntity.add(unidad.convertToUnidadEntity(unidad)));

        cursoEntity.setUnidades(unidadesEntity);

        return cursoEntity;

    }
}
