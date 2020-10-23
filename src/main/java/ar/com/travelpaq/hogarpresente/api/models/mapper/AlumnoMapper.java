package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.domain.Alumno;
import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import org.springframework.stereotype.Component;

@Component
public class AlumnoMapper {
    public Alumno mapAlumnoEntityToAlumno(AlumnoEntity alumnoEntity) {
        Alumno alumno = new Alumno();
        alumno.setId(alumnoEntity.getId());
        alumno.setNombre(alumnoEntity.getNombre());
        alumno.setApellido(alumnoEntity.getApellido());
        alumno.setCorreo(alumnoEntity.getCorreo());
        alumno.setClave(alumnoEntity.getClave());
        alumno.setFoto(alumnoEntity.getFoto());
        alumno.setInscripciones(alumnoEntity.getInscripciones());
        return alumno;
    }
    public AlumnoEntity mapAlumnoToAlumnoEntity(Alumno alumno){
        AlumnoEntity alumnoEntity= new AlumnoEntity();
        alumnoEntity.setId(alumno.getId());
        alumnoEntity.setNombre(alumno.getNombre());
        alumnoEntity.setApellido(alumno.getApellido());
        alumnoEntity.setCorreo(alumno.getCorreo());
        alumnoEntity.setClave(alumno.getClave());
        alumnoEntity.setFoto(alumno.getFoto());
        alumnoEntity.setInscripciones(alumno.getInscripciones());
        return alumnoEntity;
    }
}
