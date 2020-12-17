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

    public InscripcionEntity mapInscripcionDtoToInscripcionEntity(InscripcionDto inscripcionDto)
    {
        InscripcionEntity inscripcionEntity = new InscripcionEntity();
        inscripcionEntity.setId(inscripcionDto.getId());
        inscripcionEntity.setInscripcionAt(inscripcionEntity.getInscripcionAt());
        return inscripcionEntity;
    }
}
