package ar.com.travelpaq.hogarpresente.api.security.dto;

import ar.com.travelpaq.hogarpresente.api.cloudinary.dto.ImagenDto;
import ar.com.travelpaq.hogarpresente.api.security.dto.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MuestraUsuarioDto {
    private long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String clave;
    private int edad;
    private String estudio;
    private Set<RoleDto> rol;
    private List<Long> id_cursos_creados;
    private String imagen;
}
