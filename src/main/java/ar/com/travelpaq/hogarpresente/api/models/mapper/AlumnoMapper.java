package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.dto.AlumnoDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.RoleDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.RoleEntity;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class AlumnoMapper {
    public AlumnoDto mapAlumnoEntityToAlumno(AlumnoEntity alumnoEntity) {
        AlumnoDto alumnoDto = new AlumnoDto();
        alumnoDto.setId(alumnoEntity.getId());
        alumnoDto.setNombre(alumnoEntity.getNombre());
        alumnoDto.setApellido(alumnoEntity.getApellido());
        alumnoDto.setCorreo(alumnoEntity.getCorreo());
        alumnoDto.setClave(alumnoEntity.getClave());
        alumnoDto.setFoto(alumnoEntity.getFoto());

        List<RoleDto> rolesDominio = new ArrayList<>();
        List<RoleEntity> rolesEntity = alumnoEntity.getRoles();
        rolesEntity.forEach(roleEntity -> rolesDominio.add(roleEntity.convertToRole(roleEntity)));
        alumnoDto.setRoleDtos(rolesDominio);

        /*
        Set<Inscripcion> inscripcioesDominio = new HashSet<>();

        Set<InscripcionEntity> inscripcionEntities = alumnoEntity.getInscripciones();

        inscripcionEntities.forEach(inscripcionEntity -> inscripcioesDominio.add(inscripcionEntity.convertToInscripcion(inscripcionEntity)));

        alumno.setInscripciones(inscripcioesDominio);
        */
        return alumnoDto;
    }
    public AlumnoEntity mapAlumnoToAlumnoEntity(AlumnoDto alumnoDto){
        AlumnoEntity alumnoEntity= new AlumnoEntity();
        alumnoEntity.setId(alumnoDto.getId());
        alumnoEntity.setNombre(alumnoDto.getNombre());
        alumnoEntity.setApellido(alumnoDto.getApellido());
        alumnoEntity.setCorreo(alumnoDto.getCorreo());
        alumnoEntity.setClave(alumnoDto.getClave());
        alumnoEntity.setFoto(alumnoDto.getFoto());

        List<RoleEntity> rolesEntity = new ArrayList<>();
        List<RoleDto> rolesDominio = alumnoDto.getRoleDtos();
        rolesDominio.forEach(role -> rolesEntity.add(role.convertToRoleEntity(role)));
        alumnoEntity.setRoles(rolesEntity);

        /*
        Set<InscripcionEntity> inscripcionesEntity = new HashSet<>();

        Set<Inscripcion> inscripciones = alumno.getInscripciones();

        inscripciones.forEach(inscripcion -> inscripcionesEntity.add(inscripcion.convertToInscripcionEntity(inscripcion)));

        alumnoEntity.setInscripciones(inscripcionesEntity);
        */

        return alumnoEntity;
    }
}
