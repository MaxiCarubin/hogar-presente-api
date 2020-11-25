package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.dto.CursoDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.InscripcionDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.UnidadDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.InscripcionEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CursoMapper {

    @Autowired
    private InscripcionMapper inscripcionMapper;

    @Autowired
    private UnidadMapper unidadMapper;

    public CursoDto mapCursoEntityToCurso(CursoEntity cursoEntity){
        CursoDto cursoDto = new CursoDto();
        cursoDto.setId(cursoEntity.getId());
        cursoDto.setNombre(cursoEntity.getNombre());
        cursoDto.setDescripcion(cursoEntity.getDescripcion());
        cursoDto.setCapacitador(cursoEntity.getCapacitador());
        cursoDto.setPrecio(cursoEntity.getPrecio());
        cursoDto.setImagen(cursoEntity.getImagen());

//        Set<InscripcionDto> inscripcioesDominio = new HashSet<>();
//        Set<InscripcionEntity> inscripcionesEntity = cursoEntity.getInscripciones();
//        for (InscripcionEntity inscripcionEntity : inscripcionesEntity) {
//            inscripcioesDominio.add(inscripcionMapper.mapInscripcionEntityToInscripcion(inscripcionEntity));
//        }
//        cursoDto.setInscripciones(inscripcioesDominio);
//
//        List<UnidadDto> unidadesDominio = new ArrayList<>();
//        List<UnidadEntity> undadesEntity = cursoEntity.getUnidades();
//        for (UnidadEntity unidadEntity : undadesEntity) {
//            unidadesDominio.add(unidadMapper.mapUnidadEntityToUnidad(unidadEntity));
//        }
//        cursoDto.setUnidades(unidadesDominio);

        return cursoDto;
    }
    public CursoEntity mapCursoToCursoEntity(CursoDto cursoDto){
        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setId(cursoDto.getId());
        cursoEntity.setNombre(cursoDto.getNombre());
        cursoEntity.setDescripcion(cursoDto.getDescripcion());
        cursoEntity.setCapacitador(cursoDto.getCapacitador());
        cursoEntity.setPrecio(cursoDto.getPrecio());
        cursoEntity.setImagen(cursoDto.getImagen());

//        Set<InscripcionEntity> inscripcionesEntity = new HashSet<>();
//        Set<InscripcionDto> inscripcionesDto = cursoDto.getInscripciones();
//        for (InscripcionDto inscripcionDto : inscripcionesDto) {
//            inscripcionesEntity.add(inscripcionMapper.mapInscripcionToInscripcionEntity(inscripcionDto));
//        }
//        cursoEntity.setInscripciones(inscripcionesEntity);
//
//        List<UnidadEntity> unidadesEntity = new ArrayList<>();
//        List<UnidadDto> unidadesDominio = cursoDto.getUnidades();
//        for (UnidadDto unidad : unidadesDominio) {
//            unidadesEntity.add(unidadMapper.mapUnidadToUnidadEntity(unidad));
//        }
//        cursoEntity.setUnidades(unidadesEntity);

        return cursoEntity;
    }
}
