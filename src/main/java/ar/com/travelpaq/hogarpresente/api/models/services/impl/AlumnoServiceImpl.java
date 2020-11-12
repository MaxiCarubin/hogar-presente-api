package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.dto.AlumnoDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.Mensaje;
import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import ar.com.travelpaq.hogarpresente.api.models.mapper.AlumnoMapper;
import ar.com.travelpaq.hogarpresente.api.models.repository.IAlumnoRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.IAlumnoService;
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
public class AlumnoServiceImpl implements IAlumnoService {

    private Logger logger = LoggerFactory.getLogger(AlumnoServiceImpl.class);

    @Autowired
    private IAlumnoRepository alumnoRepository;

    @Autowired
    private AlumnoMapper alumnoMapper;

    @Override
    public Optional<AlumnoEntity> getByCorreo(String correo) {
        return alumnoRepository.findByCorreo(correo);
    }

    public boolean existsByCorreo(String correo){
        return alumnoRepository.existsByCorreo(correo);
    }

    public void save(AlumnoEntity alumnoEntity){
        alumnoRepository.save(alumnoEntity);
    }

    @Override
    public ResponseEntity<List<AlumnoEntity>> findAll() {
        List<AlumnoEntity> alumnoEntities = alumnoRepository.findAll();
        List<AlumnoDto> alumnoDtos = new ArrayList<>();

        alumnoEntities.forEach(alumnoEntity -> alumnoDtos.add(alumnoMapper.mapAlumnoEntityToAlumno(alumnoEntity)));

        return new ResponseEntity(alumnoDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (!alumnoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        AlumnoEntity alumnoEntity = alumnoRepository.getOne(id);
        return new ResponseEntity(alumnoEntity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(AlumnoDto alumnoDto) {
        if(StringUtils.isBlank(alumnoDto.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(alumnoDto.getApellido()))
            return new ResponseEntity(new Mensaje("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isEmpty(alumnoDto.getClave()))
            return new ResponseEntity(new Mensaje("La clave no puede estar vacia"), HttpStatus.BAD_REQUEST);
        if(alumnoRepository.existsByCorreo(alumnoDto.getCorreo()))
            return new ResponseEntity(new Mensaje("Ese correo ya existe"), HttpStatus.BAD_REQUEST);
        AlumnoEntity alumnoEntity = alumnoMapper.mapAlumnoToAlumnoEntity(alumnoDto);
        alumnoRepository.save(alumnoEntity);
        return new ResponseEntity(new Mensaje("Alumno creado"), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(AlumnoDto alumnoDto, Long id) {
        if (!alumnoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("no existe el alumno en la base de datos"), HttpStatus.NOT_FOUND);
        AlumnoEntity alumnoEntity = alumnoRepository.getOne(id);
        alumnoEntity.setNombre(alumnoDto.getNombre());
        alumnoEntity.setApellido(alumnoDto.getApellido());
        alumnoEntity.setCorreo(alumnoDto.getCorreo());
        alumnoEntity.setClave(alumnoDto.getClave());
        alumnoEntity.setFoto(alumnoDto.getFoto());

        alumnoRepository.save(alumnoEntity);
        return new ResponseEntity(new Mensaje("Alumno Actualizado!"), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(Long id) {
        if (!alumnoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        alumnoRepository.deleteById(id);
        return new ResponseEntity(new Mensaje("Alumno Eliminado!"), HttpStatus.OK);
    }
}
