package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.domain.Alumno;
import ar.com.travelpaq.hogarpresente.api.models.domain.Role;
import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.RoleEntity;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class AlumnoMapper {
    public Alumno mapAlumnoEntityToAlumno(AlumnoEntity alumnoEntity) {
        Alumno alumno = new Alumno();
        alumno.setId(alumnoEntity.getId());
        alumno.setNombre(alumnoEntity.getNombre());
        alumno.setApellido(alumnoEntity.getApellido());
        alumno.setCorreo(alumnoEntity.getCorreo());
        alumno.setClave(alumnoEntity.getClave());
        alumno.setFoto(alumnoEntity.getFoto());

        List<Role> rolesDominio = new ArrayList<>();
        List<RoleEntity> rolesEntity = alumnoEntity.getRoles();
        rolesEntity.forEach(roleEntity -> rolesDominio.add(roleEntity.convertToRole(roleEntity)));
        alumno.setRoles(rolesDominio);

        /*
        Set<Inscripcion> inscripcioesDominio = new HashSet<>();

        Set<InscripcionEntity> inscripcionEntities = alumnoEntity.getInscripciones();

        inscripcionEntities.forEach(inscripcionEntity -> inscripcioesDominio.add(inscripcionEntity.convertToInscripcion(inscripcionEntity)));

        alumno.setInscripciones(inscripcioesDominio);
        */
        return alumno;
    }
    public AlumnoEntity mapAlumnoToAlumnoEntity(Alumno alumno){
        AlumnoEntity alumnoEntity= new AlumnoEntity();
        alumnoEntity.setId(alumno.getId());
        alumnoEntity.setNombre(alumno.getNombre());
        alumnoEntity.setApellido(alumno.getApellido());
        alumnoEntity.setCorreo(alumno.getCorreo());
        alumnoEntity.setClave(alumno.getClave());
        alumnoEntity.setFoto(alumno.getFoto());

        List<RoleEntity> rolesEntity = new ArrayList<>();
        List<Role> rolesDominio = alumno.getRoles();
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
