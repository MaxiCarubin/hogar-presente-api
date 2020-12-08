package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.dto.InscripcionDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.InscripcionEntity;
import ar.com.travelpaq.hogarpresente.api.security.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InscripcionMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private CursoMapper cursoMapper;

    public InscripcionDto mapInscripcionEntityToInscripcion(InscripcionEntity inscripcionEntity)
    {
        InscripcionDto inscripcionDto = new InscripcionDto();
        inscripcionDto.setId(inscripcionEntity.getId());
        inscripcionDto.setInscripcionAt(inscripcionEntity.getInscripcionAt());
//        inscripcionDto.setAlumno(alumnoMapper.mapAlumnoEntityToAlumno(inscripcionEntity.getAlumno()));
//        inscripcionDto.setCurso(cursoMapper.mapCursoEntityToCurso(inscripcionEntity.getCurso()));
        return inscripcionDto;
    }
    public InscripcionEntity mapInscripcionToInscripcionEntity(InscripcionDto inscripcionDto)
    {
        InscripcionEntity inscripcionEntity = new InscripcionEntity();
        inscripcionEntity.setId(inscripcionDto.getId());
        inscripcionEntity.setInscripcionAt(inscripcionEntity.getInscripcionAt());
//        inscripcionEntity.setAlumno(alumnoMapper.mapAlumnoToAlumnoEntity(inscripcionDto.getAlumno()));
//        inscripcionEntity.setCurso(cursoMapper.mapCursoToCursoEntity(inscripcionDto.getCurso()));

        return inscripcionEntity;
    }
}
