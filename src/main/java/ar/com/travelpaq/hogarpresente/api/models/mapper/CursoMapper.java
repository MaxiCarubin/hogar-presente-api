package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.dto.*;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        cursoDto.setPrecio(cursoEntity.getPrecio());
        cursoDto.setImagen(cursoEntity.getImagen());
        cursoDto.setCategoria(cursoEntity.getCategoria());
        cursoDto.setHabilitado(cursoEntity.isHabilitado());
        return cursoDto;
    }
    public CursoEntity mapCursoToCursoEntity(CursoDto cursoDto){
        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setId(cursoDto.getId());
        cursoEntity.setNombre(cursoDto.getNombre());
        cursoEntity.setDescripcion(cursoDto.getDescripcion());
        cursoEntity.setPrecio(cursoDto.getPrecio());
        cursoEntity.setImagen(cursoDto.getImagen());
        cursoEntity.setCategoria(cursoDto.getCategoria());
        cursoEntity.setHabilitado(cursoDto.isHabilitado());
        return cursoEntity;
    }

    public CompletoCursoDto mapCursoDtoToCursoEntity(CursoEntity cursoEntity){
        CompletoCursoDto cursoDto = new CompletoCursoDto();
        cursoDto.setId(cursoEntity.getId());
        cursoDto.setNombre(cursoEntity.getNombre());
        cursoDto.setDescripcion(cursoEntity.getDescripcion());
        cursoDto.setCategoria(cursoEntity.getCategoria());
        cursoDto.setImagen(cursoEntity.getImagen());
        cursoDto.setPrecio(cursoEntity.getPrecio());
        cursoDto.setId_usuario_creador(cursoEntity.getUsuario().getId());
        cursoDto.setUnidades(cursoEntity.getUnidades());
        return cursoDto;
    }
}
