package ar.com.travelpaq.hogarpresente.api.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MuestraAlumnoDto {
    private long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String imagen;
}
