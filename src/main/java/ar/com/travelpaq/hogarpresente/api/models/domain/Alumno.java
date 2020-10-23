package ar.com.travelpaq.hogarpresente.api.models.domain;

import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
        alumnoEntity.setInscripciones(inscripciones);

        return alumnoEntity;
    }
}
