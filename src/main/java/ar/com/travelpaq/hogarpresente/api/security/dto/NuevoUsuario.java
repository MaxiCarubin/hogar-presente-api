package ar.com.travelpaq.hogarpresente.api.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NuevoUsuario {
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @Email
    private String correo;
    @NotBlank
    private String clave;
    @NotNull
    private int edad;
    @NotBlank
    private String estudios;
    @NotBlank
    private String foto;
    private Set<String> roles = new HashSet<>();
}
