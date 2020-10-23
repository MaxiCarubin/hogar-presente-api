package ar.com.travelpaq.hogarpresente.api.models.domain;

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

}
