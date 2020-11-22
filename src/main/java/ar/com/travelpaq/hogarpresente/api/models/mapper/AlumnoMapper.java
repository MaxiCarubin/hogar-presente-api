package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.dto.AlumnoDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.InscripcionDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.InscripcionEntity;
import ar.com.travelpaq.hogarpresente.api.models.repository.IAlumnoRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.IAlumnoService;
import ar.com.travelpaq.hogarpresente.api.security.dto.RoleDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import ar.com.travelpaq.hogarpresente.api.security.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

@Component
public class AlumnoMapper {

    @Autowired
    private InscripcionMapper inscripcionMapper;

    public AlumnoDto mapAlumnoEntityToAlumno(AlumnoEntity alumnoEntity) {

        AlumnoDto alumnoDto = new AlumnoDto();
        alumnoDto.setId(alumnoEntity.getId());
        alumnoDto.setNombre(alumnoEntity.getNombre());
        alumnoDto.setApellido(alumnoEntity.getApellido());
        alumnoDto.setCorreo(alumnoEntity.getCorreo());
        alumnoDto.setClave(alumnoEntity.getClave());
        alumnoDto.setFoto(alumnoEntity.getFoto());

//        Set<InscripcionDto> inscripcioesDominio = new HashSet<>();
//        Set<InscripcionEntity> inscripcionesEntity = alumnoEntity.getInscripciones();
//        for (InscripcionEntity inscripcionEntity : inscripcionesEntity) {
//            inscripcioesDominio.add(inscripcionMapper.mapInscripcionEntityToInscripcion(inscripcionEntity));
//        }
//        alumnoDto.setInscripciones(inscripcioesDominio);

        Set<RoleDto> rolesDominio = new HashSet<>();
        Set<RoleEntity> rolesEntity = alumnoEntity.getRoles();
        rolesEntity.forEach(roleEntity -> rolesDominio.add(roleEntity.convertToRole(roleEntity)));
        alumnoDto.setRoles(rolesDominio);

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

//        Set<InscripcionEntity> inscripcionesEntity = new HashSet<>();
//        Set<InscripcionDto> inscripcionesDto = alumnoDto.getInscripciones();
//        for (InscripcionDto inscripcionDto : inscripcionesDto) {
//            inscripcionesEntity.add(inscripcionMapper.mapInscripcionToInscripcionEntity(inscripcionDto));
//        }
//        alumnoEntity.setInscripciones(inscripcionesEntity);

        Set<RoleEntity> rolesEntity = new HashSet<>();
        Set<RoleDto> rolesDominio = alumnoDto.getRoles();
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
