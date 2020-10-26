package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.domain.Alumno;
import ar.com.travelpaq.hogarpresente.api.models.domain.Inscripcion;
import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.InscripcionEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlumnoMapper {
    public Alumno mapAlumnoEntityToAlumno(AlumnoEntity alumnoEntity) {
        Alumno alumno = new Alumno();
        List<InscripcionEntity> inscripcionEntities = new ArrayList<>();
        alumno.setId(alumnoEntity.getId());
        alumno.setNombre(alumnoEntity.getNombre());
        alumno.setApellido(alumnoEntity.getApellido());
        alumno.setCorreo(alumnoEntity.getCorreo());
        alumno.setClave(alumnoEntity.getClave());
        alumno.setFoto(alumnoEntity.getFoto());

        List<Inscripcion> inscripcioesDominio = new ArrayList<>();

        List<InscripcionEntity> inscripcionesEntity = new ArrayList<>();

        alumnoEntity.getInscripciones().forEach(inscripcionEntity -> inscripcionesEntity.add(inscripcionEntity));

        inscripcionesEntity.forEach(inscripcionEntity -> inscripcioesDominio.add(inscripcionEntity.convertToInscripcion()));

        alumno.setInscripciones(inscripcioesDominio);

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

        List<Inscripcion> inscripcioesDominio = new ArrayList<>();

        List<InscripcionEntity> inscripcionesEntity = new ArrayList<>();

        alumno.getInscripciones().forEach(inscripcion -> inscripcioesDominio.add(inscripcion));

        inscripcioesDominio.forEach(inscripcion -> inscripcionesEntity.add(inscripcion.convertToInscripcionEntity()));

        alumnoEntity.setInscripciones(inscripcionesEntity);

        return alumnoEntity;
    }
}
