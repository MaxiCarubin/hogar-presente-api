package ar.com.travelpaq.hogarpresente.api.security.mapper;

import ar.com.travelpaq.hogarpresente.api.cloudinary.dto.ImagenDto;
import ar.com.travelpaq.hogarpresente.api.cloudinary.mapper.ImagenMapper;
import ar.com.travelpaq.hogarpresente.api.models.mapper.InscripcionMapper;
import ar.com.travelpaq.hogarpresente.api.security.dto.CompletoUsuarioDto;
import ar.com.travelpaq.hogarpresente.api.security.dto.UsuarioDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import ar.com.travelpaq.hogarpresente.api.security.dto.RoleDto;
import ar.com.travelpaq.hogarpresente.api.security.entity.UsuarioEntity;
import ar.com.travelpaq.hogarpresente.api.security.entity.RoleEntity;
import ar.com.travelpaq.hogarpresente.api.security.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UsuarioMapper {

    @Autowired
    private InscripcionMapper inscripcionMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ImagenMapper imagenMapper;

    public UsuarioDto mapAlumnoEntityToAlumno(UsuarioEntity usuarioEntity) {

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuarioEntity.getId());
        usuarioDto.setNombre(usuarioEntity.getNombre());
        usuarioDto.setApellido(usuarioEntity.getApellido());
        usuarioDto.setCorreo(usuarioEntity.getCorreo());
        usuarioDto.setClave(usuarioEntity.getClave());

//        Set<InscripcionDto> inscripcioesDominio = new HashSet<>();
//        Set<InscripcionEntity> inscripcionesEntity = alumnoEntity.getInscripciones();
//        for (InscripcionEntity inscripcionEntity : inscripcionesEntity) {
//            inscripcioesDominio.add(inscripcionMapper.mapInscripcionEntityToInscripcion(inscripcionEntity));
//        }
//        alumnoDto.setInscripciones(inscripcioesDominio);

        Set<RoleDto> rolesDominio = new HashSet<>();
        Set<RoleEntity> rolesEntity = usuarioEntity.getRoles();
        rolesEntity.forEach(roleEntity -> rolesDominio.add(roleEntity.convertToRole(roleEntity)));
        usuarioDto.setRoles(rolesDominio);

        return usuarioDto;
    }
    public UsuarioEntity mapAlumnoToAlumnoEntity(UsuarioDto usuarioDto){
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(usuarioDto.getId());
        usuarioEntity.setNombre(usuarioDto.getNombre());
        usuarioEntity.setApellido(usuarioDto.getApellido());
        usuarioEntity.setCorreo(usuarioDto.getCorreo());
        usuarioEntity.setClave(usuarioDto.getClave());

//        Set<InscripcionEntity> inscripcionesEntity = new HashSet<>();
//        Set<InscripcionDto> inscripcionesDto = alumnoDto.getInscripciones();
//        for (InscripcionDto inscripcionDto : inscripcionesDto) {
//            inscripcionesEntity.add(inscripcionMapper.mapInscripcionToInscripcionEntity(inscripcionDto));
//        }
//        alumnoEntity.setInscripciones(inscripcionesEntity);

        Set<RoleEntity> rolesEntity = new HashSet<>();
        Set<RoleDto> rolesDominio = usuarioDto.getRoles();
        rolesDominio.forEach(role -> rolesEntity.add(role.convertToRoleEntity(role)));
        usuarioEntity.setRoles(rolesEntity);

        /*
        Set<InscripcionEntity> inscripcionesEntity = new HashSet<>();

        Set<Inscripcion> inscripciones = alumno.getInscripciones();

        inscripciones.forEach(inscripcion -> inscripcionesEntity.add(inscripcion.convertToInscripcionEntity(inscripcion)));

        alumnoEntity.setInscripciones(inscripcionesEntity);
        */

        return usuarioEntity;
    }

    public CompletoUsuarioDto mapUsuarioEntityToUsuarioDto(UsuarioEntity usuarioEntity){
        CompletoUsuarioDto usuarioDto = new CompletoUsuarioDto();
        usuarioDto.setId(usuarioEntity.getId());
        usuarioDto.setNombre(usuarioEntity.getNombre());
        usuarioDto.setApellido(usuarioEntity.getApellido());
        usuarioDto.setCorreo(usuarioEntity.getCorreo());
        usuarioDto.setEdad(usuarioEntity.getEdad());
        usuarioDto.setEstudio(usuarioEntity.getEstudio());
        usuarioDto.setClave(usuarioEntity.getClave());

        ImagenDto imagen = imagenMapper.mapImagenEntityToImagenDto(usuarioEntity.getImagen());
        usuarioDto.setImagen(imagen);

        Set<RoleDto> roleDtos = new HashSet<>();
        for (RoleEntity roleEntity : usuarioEntity.getRoles()){
            RoleDto roleDto = roleMapper.mapRoleDtoToRoleEntity(roleEntity);
            roleDtos.add(roleDto);
        }
        usuarioDto.setRol(roleDtos);

        List<Long> cursosCreados = new ArrayList<>();
        for (CursoEntity cursoEntity : usuarioEntity.getCursos()){
            cursosCreados.add(cursoEntity.getId());
        }
        usuarioDto.setId_cursos_creados(cursosCreados);
        return usuarioDto;
    }
}