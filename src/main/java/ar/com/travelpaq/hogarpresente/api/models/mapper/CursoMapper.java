package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.domain.Curso;
import ar.com.travelpaq.hogarpresente.api.models.domain.Inscripcion;
import ar.com.travelpaq.hogarpresente.api.models.domain.Unidad;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.InscripcionEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CursoMapper {
    public Curso mapCursoEntityToCurso(CursoEntity cursoEntity){
        Curso curso = new Curso();
        curso.setId(cursoEntity.getId());
        curso.setNombre(cursoEntity.getNombre());
        curso.setDescripcion(cursoEntity.getDescripcion());
        curso.setCapacitador(cursoEntity.getCapacitador());
        curso.setHoras(cursoEntity.getHoras());
        curso.setPrecio(cursoEntity.getPrecio());

        List<Inscripcion> inscripcioesDominio = new ArrayList<>();
        List<InscripcionEntity> inscripcionesEntity = cursoEntity.getInscripciones();
        inscripcionesEntity.forEach(inscripcionEntity -> inscripcioesDominio.add(inscripcionEntity.convertToInscripcion(inscripcionEntity)));
        curso.setInscripciones(inscripcioesDominio);

        List<Unidad> unidadesDominio = new ArrayList<>();
        List<UnidadEntity> undadesEntity = cursoEntity.getUnidades();
        undadesEntity.forEach(unidadEntity -> unidadesDominio.add(unidadEntity.convertToUnidad(unidadEntity)));
        curso.setUnidades(unidadesDominio);

        return curso;
    }
    public CursoEntity mapCursoToCursoEntity(Curso curso){
        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setId(curso.getId());
        cursoEntity.setNombre(curso.getNombre());
        cursoEntity.setDescripcion(curso.getDescripcion());
        cursoEntity.setCapacitador(curso.getCapacitador());
        cursoEntity.setHoras(curso.getHoras());
        cursoEntity.setPrecio(curso.getPrecio());

        List<InscripcionEntity> inscripcionesEntity = new ArrayList<>();
        List<Inscripcion> inscripcionesDominio = curso.getInscripciones();
        inscripcionesDominio.forEach(inscripcion -> inscripcionesEntity.add(inscripcion.convertToInscripcionEntity(inscripcion)));
        cursoEntity.setInscripciones(inscripcionesEntity);

        List<UnidadEntity> unidadesEntity = new ArrayList<>();
        List<Unidad> unidadesDominio = curso.getUnidades();
        unidadesDominio.forEach(unidad -> unidadesEntity.add(unidad.convertToUnidadEntity(unidad)));
        cursoEntity.setUnidades(unidadesEntity);

        return cursoEntity;
    }
}
