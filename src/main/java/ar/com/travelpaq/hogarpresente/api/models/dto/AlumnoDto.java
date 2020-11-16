package ar.com.travelpaq.hogarpresente.api.models.dto;

import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import ar.com.travelpaq.hogarpresente.api.security.entity.RoleEntity;
import ar.com.travelpaq.hogarpresente.api.security.dto.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoDto {

    private long id;

    private String nombre;

    private String apellido;

    private String correo;

    private String clave;

    private String foto;

    private Set<RoleDto> roles;

    public AlumnoEntity convertToAlumnoEntity(AlumnoDto alumnoDto){
        AlumnoEntity alumnoEntity = new AlumnoEntity();
        alumnoEntity.setId(alumnoDto.getId());
        alumnoEntity.setNombre(alumnoDto.getNombre());
        alumnoEntity.setApellido(alumnoDto.getApellido());
        alumnoEntity.setCorreo(alumnoDto.getCorreo());
        alumnoEntity.setClave(alumnoDto.getClave());
        alumnoEntity.setFoto(alumnoDto.getFoto());

        Set<RoleEntity> rolesEntity = new HashSet<>();
        Set<RoleDto> rolesDominio = alumnoDto.getRoles();
        rolesDominio.forEach(role -> rolesEntity.add(role.convertToRoleEntity(role)));
        alumnoEntity.setRoles(rolesEntity);

        /*
        Set<InscripcionEntity> inscripcionesEntity = new HashSet<>();

        Set<Inscripcion> inscripcioesDominio = inscripciones;

        inscripcioesDominio.forEach(inscripcion -> inscripcionesEntity.add(inscripcion.convertToInscripcionEntity(inscripcion)));

        alumnoEntity.setInscripciones(inscripcionesEntity);
        */
        return alumnoEntity;
    }
}
