package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.dto.CursoDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.UnidadDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CursoMapper {
    public CursoDto mapCursoEntityToCurso(CursoEntity cursoEntity){
        CursoDto cursoDto = new CursoDto();
        cursoDto.setId(cursoEntity.getId());
        cursoDto.setNombre(cursoEntity.getNombre());
        cursoDto.setDescripcion(cursoEntity.getDescripcion());
        cursoDto.setCapacitador(cursoEntity.getCapacitador());
        cursoDto.setHoras(cursoEntity.getHoras());
        cursoDto.setPrecio(cursoEntity.getPrecio());
        cursoDto.setEnabled(cursoEntity.getEnabled());
        /*
        Set<Inscripcion> inscripcioesDominio = new HashSet<>();
        Set<InscripcionEntity> inscripcionesEntity = cursoEntity.getInscripciones();
        inscripcionesEntity.forEach(inscripcionEntity -> inscripcioesDominio.add(inscripcionEntity.convertToInscripcion(inscripcionEntity)));
        curso.setInscripciones(inscripcioesDominio);
        */
        List<UnidadDto> unidadesDominio = new ArrayList<>();
        List<UnidadEntity> undadesEntity = cursoEntity.getUnidades();
        undadesEntity.forEach(unidadEntity -> unidadesDominio.add(unidadEntity.convertToUnidad(unidadEntity)));
        cursoDto.setUnidades(unidadesDominio);

        return cursoDto;
    }
    public CursoEntity mapCursoToCursoEntity(CursoDto cursoDto){
        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setId(cursoDto.getId());
        cursoEntity.setNombre(cursoDto.getNombre());
        cursoEntity.setDescripcion(cursoDto.getDescripcion());
        cursoEntity.setCapacitador(cursoDto.getCapacitador());
        cursoEntity.setHoras(cursoDto.getHoras());
        cursoEntity.setPrecio(cursoDto.getPrecio());
        cursoEntity.setEnabled(cursoDto.getEnabled());
        /*
        Set<InscripcionEntity> inscripcionesEntity = new HashSet<>();
        Set<Inscripcion> inscripcionesDominio = curso.getInscripciones();
        inscripcionesDominio.forEach(inscripcion -> inscripcionesEntity.add(inscripcion.convertToInscripcionEntity(inscripcion)));
        cursoEntity.setInscripciones(inscripcionesEntity);
        */
        List<UnidadEntity> unidadesEntity = new ArrayList<>();
        List<UnidadDto> unidadesDominio = cursoDto.getUnidades();
        unidadesDominio.forEach(unidad -> unidadesEntity.add(unidad.convertToUnidadEntity(unidad)));
        cursoEntity.setUnidades(unidadesEntity);

        return cursoEntity;
    }
}
