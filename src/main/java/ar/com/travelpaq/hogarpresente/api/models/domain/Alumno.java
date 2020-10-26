package ar.com.travelpaq.hogarpresente.api.models.domain;

import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.InscripcionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    private List<Inscripcion> inscripciones;

    public AlumnoEntity convertToAlumnoEntity(){
        AlumnoEntity alumnoEntity = new AlumnoEntity();
        alumnoEntity.setId(id);
        alumnoEntity.setNombre(nombre);
        alumnoEntity.setApellido(apellido);
        alumnoEntity.setCorreo(correo);
        alumnoEntity.setClave(clave);
        alumnoEntity.setFoto(foto);

        List<Inscripcion> inscripcioesDominio = new ArrayList<>();

        List<InscripcionEntity> inscripcionesEntity = new ArrayList<>();

        inscripciones.forEach(inscripcion -> inscripcioesDominio.add(inscripcion));

        inscripcioesDominio.forEach(inscripcion -> inscripcionesEntity.add(inscripcion.convertToInscripcionEntity()));

        alumnoEntity.setInscripciones(inscripcionesEntity);

        return alumnoEntity;
    }
}
