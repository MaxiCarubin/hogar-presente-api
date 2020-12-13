package ar.com.travelpaq.hogarpresente.api.security.service.impl;

import ar.com.travelpaq.hogarpresente.api.cloudinary.entity.ImagenEntity;
import ar.com.travelpaq.hogarpresente.api.cloudinary.repository.IImagenRepository;
import ar.com.travelpaq.hogarpresente.api.security.dto.CompletoUsuarioDto;
import ar.com.travelpaq.hogarpresente.api.security.dto.UsuarioDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.Mensaje;
import ar.com.travelpaq.hogarpresente.api.security.entity.UsuarioEntity;
import ar.com.travelpaq.hogarpresente.api.security.mapper.UsuarioMapper;
import ar.com.travelpaq.hogarpresente.api.models.mapper.InscripcionMapper;
import ar.com.travelpaq.hogarpresente.api.security.repository.IUsuarioRepository;
import ar.com.travelpaq.hogarpresente.api.security.service.IUsuarioService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional
public class UsuarioServiceImpl implements IUsuarioService {

    private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private InscripcionMapper inscripcionMapper;

    @Autowired
    private IImagenRepository imagenRepository;

    @Override
    public Optional<UsuarioEntity> getByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    public boolean existsByCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    public void save(UsuarioEntity usuarioEntity) {
        usuarioRepository.save(usuarioEntity);
    }

    @Override
    public ResponseEntity<?> findAll() {
        List<UsuarioEntity> usuarioEntities = usuarioRepository.findAll();
        List<CompletoUsuarioDto> usuarioDtos = new ArrayList<>();
        for (UsuarioEntity usuarioEntity : usuarioEntities) {
            CompletoUsuarioDto usuarioDto = usuarioMapper.mapUsuarioEntityToUsuarioDto(usuarioEntity);
            usuarioDtos.add(usuarioDto);
        }
        return new ResponseEntity(usuarioDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (!usuarioRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el usuario en la base de datos"), HttpStatus.NOT_FOUND);
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).get();
        CompletoUsuarioDto usuarioDto = usuarioMapper.mapUsuarioEntityToUsuarioDto(usuarioEntity);
        return new ResponseEntity(usuarioDto, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(UsuarioDto usuarioDto, Long id) {
        if (!usuarioRepository.existsById(id))
            return new ResponseEntity(new Mensaje("no existe el usuario en la base de datos"), HttpStatus.NOT_FOUND);
        UsuarioEntity usuarioEntity = usuarioRepository.getOne(id);
        if (!usuarioDto.getNombre().isEmpty())
            usuarioEntity.setNombre(usuarioDto.getNombre());
        if (!usuarioDto.getApellido().isEmpty())
        usuarioEntity.setApellido(usuarioDto.getApellido());
        if (!usuarioDto.getCorreo().isEmpty())
            usuarioEntity.setCorreo(usuarioDto.getCorreo());
        if (!usuarioDto.getClave().isEmpty())
            usuarioEntity.setClave(usuarioDto.getClave());
        if (!usuarioDto.getEstudio().isEmpty())
            usuarioEntity.setEstudio(usuarioDto.getEstudio());
        if(usuarioDto.getImagen() !=null){
            imagenRepository.deleteById(usuarioEntity.getImagen().getId());
            usuarioEntity.setImagen(usuarioDto.getImagen());
        }

        usuarioEntity.setEdad(usuarioDto.getEdad());
        usuarioRepository.save(usuarioEntity);
        return new ResponseEntity(new Mensaje("Usuario Actualizado!"), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(Long id) {
        if (!usuarioRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        usuarioRepository.deleteById(id);
        return new ResponseEntity(new Mensaje("Usuario Eliminado!"), HttpStatus.OK);
    }
}
