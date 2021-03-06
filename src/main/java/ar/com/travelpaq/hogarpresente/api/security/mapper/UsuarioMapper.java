package ar.com.travelpaq.hogarpresente.api.security.mapper;

import ar.com.travelpaq.hogarpresente.api.cloudinary.mapper.ImagenMapper;
import ar.com.travelpaq.hogarpresente.api.models.mapper.InscripcionMapper;
import ar.com.travelpaq.hogarpresente.api.security.dto.MuestraUsuarioDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import ar.com.travelpaq.hogarpresente.api.security.dto.RoleDto;
import ar.com.travelpaq.hogarpresente.api.security.entity.UsuarioEntity;
import ar.com.travelpaq.hogarpresente.api.security.entity.RoleEntity;
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

    public MuestraUsuarioDto mapUsuarioEntityToMuestraUsuarioDto(UsuarioEntity usuarioEntity){
        MuestraUsuarioDto usuarioDto = new MuestraUsuarioDto();
        usuarioDto.setId(usuarioEntity.getId());
        usuarioDto.setNombre(usuarioEntity.getNombre());
        usuarioDto.setApellido(usuarioEntity.getApellido());
        usuarioDto.setCorreo(usuarioEntity.getCorreo());
        usuarioDto.setEdad(usuarioEntity.getEdad());
        usuarioDto.setEstudio(usuarioEntity.getEstudio());
        usuarioDto.setClave(usuarioEntity.getClave());
        usuarioDto.setImagen(usuarioEntity.getImagen().getImagenUrl());

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
