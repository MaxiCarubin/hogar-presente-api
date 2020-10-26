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

        List<InscripcionEntity> inscripcionesEntity = new ArrayList<>();

        cursoEntity.getInscripciones().forEach(inscripcionEntity -> inscripcionesEntity.add(inscripcionEntity));

        inscripcionesEntity.forEach(inscripcionEntity -> inscripcioesDominio.add(inscripcionEntity.convertToInscripcion()));

        curso.setInscripciones(inscripcioesDominio);

        List<Unidad> unidaddesDominio = new ArrayList<>();

        List<UnidadEntity> unidadesEntity = new ArrayList<>();

        cursoEntity.getUnidades().forEach(unidadEntity -> unidadesEntity.add(unidadEntity));

        unidadesEntity.forEach(unidadEntity -> unidaddesDominio.add(unidadEntity.convertToUnidad()));

        curso.setUnidades(unidaddesDominio);

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

        List<Inscripcion> inscripcioesDominio = new ArrayList<>();

        List<InscripcionEntity> inscripcionesEntity = new ArrayList<>();

        curso.getInscripciones().forEach(inscripcion -> inscripcioesDominio.add(inscripcion));

        inscripcioesDominio.forEach(inscripcion -> inscripcionesEntity.add(inscripcion.convertToInscripcionEntity()));

        cursoEntity.setInscripciones(inscripcionesEntity);

        List<Unidad> unidaddesDominio = new ArrayList<>();

        List<UnidadEntity> unidadesEntity = new ArrayList<>();

        curso.getUnidades().forEach(unidadEntity -> unidaddesDominio.add(unidadEntity));

        unidaddesDominio.forEach(unidades -> unidadesEntity.add(unidades.convertToUnidadEntity()));

        curso.setUnidades(unidaddesDominio);

        return cursoEntity;
    }
}
