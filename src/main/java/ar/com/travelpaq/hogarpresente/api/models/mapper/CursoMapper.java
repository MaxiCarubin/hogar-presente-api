package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.cloudinary.dto.ImagenDto;
import ar.com.travelpaq.hogarpresente.api.cloudinary.mapper.ImagenMapper;
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

    @Autowired
    private ImagenMapper imagenMapper;

    public NuevoCursoDto mapCursoEntityToCurso(CursoEntity cursoEntity){
        NuevoCursoDto nuevoCursoDto = new NuevoCursoDto();
        nuevoCursoDto.setId(cursoEntity.getId());
        nuevoCursoDto.setTitulo(cursoEntity.getTitulo());
        nuevoCursoDto.setSubtitulo(cursoEntity.getSubtitulo());
        nuevoCursoDto.setDescripcion(cursoEntity.getDescripcion());
        nuevoCursoDto.setPrecio(cursoEntity.getPrecio());
        nuevoCursoDto.setCategoria(cursoEntity.getCategoria());
        return nuevoCursoDto;
    }
    public CursoEntity mapCursoToCursoEntity(NuevoCursoDto nuevoCursoDto){
        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setId(nuevoCursoDto.getId());
        cursoEntity.setTitulo(nuevoCursoDto.getTitulo());
        cursoEntity.setSubtitulo(nuevoCursoDto.getSubtitulo());
        cursoEntity.setDescripcion(nuevoCursoDto.getDescripcion());
        cursoEntity.setPrecio(nuevoCursoDto.getPrecio());
        cursoEntity.setCategoria(nuevoCursoDto.getCategoria());
        return cursoEntity;
    }

    public MostrarCursoDto mapCursoDtoToCursoEntity(CursoEntity cursoEntity){
        MostrarCursoDto cursoDto = new MostrarCursoDto();
        cursoDto.setId(cursoEntity.getId());
        cursoDto.setTitulo(cursoEntity.getTitulo());
        cursoDto.setSubtitulo(cursoEntity.getSubtitulo());
        cursoDto.setDescripcion(cursoEntity.getDescripcion());
        cursoDto.setCategoria(cursoEntity.getCategoria());
        cursoDto.setPrecio(cursoEntity.getPrecio());
        cursoDto.setId_usuario_creador(cursoEntity.getUsuario().getId());
        cursoDto.setUnidades(cursoEntity.getUnidades());
        cursoDto.setHabilitado(cursoEntity.isHabilitado());
        ImagenDto imagen = imagenMapper.mapImagenEntityToImagenDto(cursoEntity.getImagen());
        cursoDto.setImagen(imagen.getImagenUrl());
        return cursoDto;
    }
}
