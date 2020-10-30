package ar.com.travelpaq.hogarpresente.api.models.domain;

import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.InscripcionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {

    private long id;

    private String nombre;

    private String apellido;

    private String correo;

    private String clave;

    private String foto;

    private Set<Inscripcion> inscripciones;

    public AlumnoEntity convertToAlumnoEntity(Alumno alumno){
        AlumnoEntity alumnoEntity = new AlumnoEntity();
        alumnoEntity.setId(alumno.getId());
        alumnoEntity.setNombre(alumno.getNombre());
        alumnoEntity.setApellido(alumno.getApellido());
        alumnoEntity.setCorreo(alumno.getCorreo());
        alumnoEntity.setClave(alumno.getClave());
        alumnoEntity.setFoto(alumno.getFoto());

        Set<InscripcionEntity> inscripcionesEntity = new HashSet<>();

        Set<Inscripcion> inscripcioesDominio = inscripciones;

        inscripcioesDominio.forEach(inscripcion -> inscripcionesEntity.add(inscripcion.convertToInscripcionEntity(inscripcion)));

        alumnoEntity.setInscripciones(inscripcionesEntity);

        return alumnoEntity;
    }
}
