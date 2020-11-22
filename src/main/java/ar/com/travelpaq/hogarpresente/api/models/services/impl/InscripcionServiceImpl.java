package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.dto.InscripcionDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.Mensaje;
import ar.com.travelpaq.hogarpresente.api.models.entity.InscripcionEntity;
import ar.com.travelpaq.hogarpresente.api.models.mapper.AlumnoMapper;
import ar.com.travelpaq.hogarpresente.api.models.mapper.CursoMapper;
import ar.com.travelpaq.hogarpresente.api.models.mapper.InscripcionMapper;
import ar.com.travelpaq.hogarpresente.api.models.repository.IAlumnoRepository;
import ar.com.travelpaq.hogarpresente.api.models.repository.ICursoRepository;
import ar.com.travelpaq.hogarpresente.api.models.repository.IInscripcionRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.IInscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InscripcionServiceImpl implements IInscripcionService {

    @Autowired
    private IInscripcionRepository inscripcionRepository;

    @Autowired
    private AlumnoMapper alumnoMapper;

    @Autowired
    private CursoMapper cursoMapper;

    @Autowired
    private IAlumnoRepository alumnoRepository;

    @Autowired
    private ICursoRepository cursoRepository;

    @Autowired
    private InscripcionMapper inscripcionMapper;

    @Override
    public ResponseEntity<List<InscripcionDto>> findAll() {
        List<InscripcionEntity> inscripcionEntities = inscripcionRepository.findAll();
        return new ResponseEntity(inscripcionEntities, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (!inscripcionRepository.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        InscripcionEntity inscripcionEntity = inscripcionRepository.getOne(id);
        return new ResponseEntity(inscripcionEntity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(InscripcionDto inscripcionDto) {
        if(inscripcionDto.getInscripcionAt() == null)
            return new ResponseEntity(new Mensaje("Debe contntener una fecha"), HttpStatus.BAD_REQUEST);
        if(!alumnoRepository.existsById(inscripcionDto.getAlumnoId()))
            return new ResponseEntity(new Mensaje("El alumno debe existir en la base de datos"), HttpStatus.BAD_REQUEST);
        if(!cursoRepository.existsById(inscripcionDto.getCursoId()))
            return new ResponseEntity(new Mensaje("El curso debe existir en la base de datos"), HttpStatus.BAD_REQUEST);
        InscripcionEntity inscripcionEntity = inscripcionMapper.mapInscripcionToInscripcionEntity(inscripcionDto);
        inscripcionEntity.setAlumno(alumnoRepository.findById(inscripcionDto.getAlumnoId()).orElse(null));
        inscripcionEntity.setCurso(cursoRepository.findById(inscripcionDto.getCursoId()).orElse(null));
        inscripcionRepository.save(inscripcionEntity);
        return new ResponseEntity(new Mensaje("Inscripción creada!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(InscripcionDto inscripcionDto, Long id) {
        if (!inscripcionRepository.existsById(id))
            return new ResponseEntity(new Mensaje("no existe la inscripción en la base de datos"), HttpStatus.NOT_FOUND);
        InscripcionEntity inscripcionEntity = inscripcionRepository.getOne(id);
        inscripcionEntity.setInscripcionAt(inscripcionDto.getInscripcionAt());
        inscripcionEntity.setAlumno(alumnoRepository.findById(inscripcionDto.getAlumnoId()).orElse(null));
        inscripcionEntity.setCurso(cursoRepository.findById(inscripcionDto.getCursoId()).orElse(null));
        inscripcionRepository.save(inscripcionEntity);
        return new ResponseEntity(new Mensaje("Inscripción Actualizada!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (!inscripcionRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        inscripcionRepository.deleteById(id);
        return new ResponseEntity(new Mensaje("Inscripción Eliminada!"), HttpStatus.OK);
    }
}

