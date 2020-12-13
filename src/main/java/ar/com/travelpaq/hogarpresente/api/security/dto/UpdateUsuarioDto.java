package ar.com.travelpaq.hogarpresente.api.security.dto;

import ar.com.travelpaq.hogarpresente.api.cloudinary.entity.ImagenEntity;
import ar.com.travelpaq.hogarpresente.api.security.dto.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

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
    private Set<RoleDto> roles;

}