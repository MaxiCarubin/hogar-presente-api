package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.domain.Inscripcion;
import ar.com.travelpaq.hogarpresente.api.models.entity.InscripcionEntity;
import org.springframework.stereotype.Component;

@Component
public class InscripcionMapper {
    public Inscripcion mapInscripcionEntityToInscripcion(InscripcionEntity inscripcionEntity)
    {
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setInscripcionAt(inscripcionEntity.getInscripcionAt());
        inscripcion.setAlumno(inscripcionEntity.getAlumnoEntity().convertToAlumno());
        inscripcion.setCurso(inscripcionEntity.getCursoEntity().convertToCurso());

        return inscripcion;
    }
    public InscripcionEntity mapInscripcionToInscripcionEntity(Inscripcion inscripcion)
    {
        InscripcionEntity inscripcionEntity = new InscripcionEntity();
        inscripcionEntity.setInscripcionAt(inscripcionEntity.getInscripcionAt());
        inscripcionEntity.setAlumnoEntity(inscripcion.getAlumno().convertToAlumnoEntity());
        inscripcionEntity.setCursoEntity(inscripcion.getCurso().convertToCursoEntity());

        return inscripcionEntity;
    }
}
