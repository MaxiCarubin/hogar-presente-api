package ar.com.travelpaq.hogarpresente.api.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUsuarioDto {
    private String nombre;
    private String apellido;
    private String correo;
    private String clave;
    private int edad;
    private String estudio;
}
