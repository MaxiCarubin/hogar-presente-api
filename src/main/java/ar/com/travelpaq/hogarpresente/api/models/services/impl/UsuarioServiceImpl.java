package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.dto.UsuarioDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.Mensaje;
import ar.com.travelpaq.hogarpresente.api.models.entity.UsuarioEntity;
import ar.com.travelpaq.hogarpresente.api.models.mapper.UsuarioMapper;
import ar.com.travelpaq.hogarpresente.api.models.mapper.InscripcionMapper;
import ar.com.travelpaq.hogarpresente.api.models.repository.IUsuarioRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.IUsuarioService;
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

    @Override
    public Optional<UsuarioEntity> getByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    public boolean existsByCorreo(String correo){
        return usuarioRepository.existsByCorreo(correo);
    }

    public void save(UsuarioEntity usuarioEntity){
        usuarioRepository.save(usuarioEntity);
    }

    @Override
    public ResponseEntity<?> findAll() {
        List<UsuarioEntity> usuarioEntities = usuarioRepository.findAll();
        return new ResponseEntity(usuarioEntities, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (!usuarioRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el alumno en la base de datos"), HttpStatus.NOT_FOUND);
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).get();
        return new ResponseEntity(usuarioEntity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(UsuarioDto usuarioDto) {
        if(StringUtils.isBlank(usuarioDto.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(usuarioDto.getApellido()))
            return new ResponseEntity(new Mensaje("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isEmpty(usuarioDto.getClave()))
            return new ResponseEntity(new Mensaje("La clave no puede estar vacia"), HttpStatus.BAD_REQUEST);
        if(usuarioRepository.existsByCorreo(usuarioDto.getCorreo()))
            return new ResponseEntity(new Mensaje("Ese correo ya existe"), HttpStatus.BAD_REQUEST);
        UsuarioEntity usuarioEntity = usuarioMapper.mapAlumnoToAlumnoEntity(usuarioDto);
        usuarioRepository.save(usuarioEntity);
        return new ResponseEntity(new Mensaje("Alumno creado"), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(UsuarioDto usuarioDto, Long id) {
        if (!usuarioRepository.existsById(id))
            return new ResponseEntity(new Mensaje("no existe el alumno en la base de datos"), HttpStatus.NOT_FOUND);
        UsuarioEntity usuarioEntity = usuarioRepository.getOne(id);
        usuarioEntity.setNombre(usuarioDto.getNombre());
        usuarioEntity.setApellido(usuarioDto.getApellido());
        usuarioEntity.setCorreo(usuarioDto.getCorreo());
        usuarioEntity.setClave(usuarioDto.getClave());
        usuarioEntity.setFoto(usuarioDto.getFoto());
        usuarioEntity.setEdad(usuarioDto.getEdad());
        usuarioEntity.setEstudio(usuarioDto.getEstudio());

//        Set<InscripcionEntity> inscripcionesEntity = new HashSet<>();
//        Set<InscripcionDto> inscripcionesDto = alumnoDto.getInscripciones();
//        for (InscripcionDto inscripcionDto : inscripcionesDto) {
//            inscripcionesEntity.add(inscripcionMapper.mapInscripcionToInscripcionEntity(inscripcionDto));
//        }
//        alumnoEntity.setInscripciones(inscripcionesEntity);

        usuarioRepository.save(usuarioEntity);
        return new ResponseEntity(new Mensaje("Alumno Actualizado!"), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(Long id) {
        if (!usuarioRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        usuarioRepository.deleteById(id);
        return new ResponseEntity(new Mensaje("Alumno Eliminado!"), HttpStatus.OK);
    }
}
