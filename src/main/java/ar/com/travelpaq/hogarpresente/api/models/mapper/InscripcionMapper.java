package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.dto.InscripcionDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.InscripcionEntity;
import org.springframework.stereotype.Component;

@Component
public class InscripcionMapper {
    public InscripcionDto mapInscripcionEntityToInscripcion(InscripcionEntity inscripcionEntity)
    {
        InscripcionDto inscripcionDto = new InscripcionDto();
        inscripcionDto.setId(inscripcionEntity.getId());
        inscripcionDto.setInscripcionAt(inscripcionEntity.getInscripcionAt());
        inscripcionDto.setAlumnoDto(inscripcionEntity.getAlumnoEntity().convertToAlumno(inscripcionEntity.getAlumnoEntity()));
        inscripcionDto.setCursoDto(inscripcionEntity.getCursoEntity().convertToCurso(inscripcionEntity.getCursoEntity()));

        return inscripcionDto;
    }
    public InscripcionEntity mapInscripcionToInscripcionEntity(InscripcionDto inscripcionDto)
    {
        InscripcionEntity inscripcionEntity = new InscripcionEntity();
        inscripcionEntity.setId(inscripcionDto.getId());
        inscripcionEntity.setInscripcionAt(inscripcionEntity.getInscripcionAt());
        inscripcionEntity.setAlumnoEntity(inscripcionDto.getAlumnoDto().convertToAlumnoEntity(inscripcionDto.getAlumnoDto()));
        inscripcionEntity.setCursoEntity(inscripcionDto.getCursoDto().convertToCursoEntity(inscripcionDto.getCursoDto()));

        return inscripcionEntity;
    }
}
