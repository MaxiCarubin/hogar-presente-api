package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.dto.InscripcionDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.Mensaje;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.InscripcionEntity;
import ar.com.travelpaq.hogarpresente.api.security.entity.UsuarioEntity;
import ar.com.travelpaq.hogarpresente.api.security.mapper.UsuarioMapper;
import ar.com.travelpaq.hogarpresente.api.models.mapper.CursoMapper;
import ar.com.travelpaq.hogarpresente.api.models.mapper.InscripcionMapper;
import ar.com.travelpaq.hogarpresente.api.security.repository.IUsuarioRepository;
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
    private UsuarioMapper usuarioMapper;

    @Autowired
    private CursoMapper cursoMapper;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private ICursoRepository cursoRepository;

    @Autowired
    private InscripcionMapper inscripcionMapper;

    @Override
    public ResponseEntity<List<InscripcionDto>> findAll() {
        List<InscripcionEntity> inscripcionEntities = inscripcionRepository.findByOrderById();
        return new ResponseEntity(inscripcionEntities, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (!inscripcionRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe la inscripcion en la base de datos"), HttpStatus.NOT_FOUND);
        InscripcionEntity inscripcionEntity = inscripcionRepository.findById(id).get();
        return new ResponseEntity(inscripcionEntity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(InscripcionDto inscripcionDto) {
        if(inscripcionDto.getInscripcionAt() == null)
            return new ResponseEntity(new Mensaje("Debe contntener una fecha"), HttpStatus.BAD_REQUEST);
        if(!usuarioRepository.existsById(inscripcionDto.getUsuarioId()))
            return new ResponseEntity(new Mensaje("El alumno debe existir en la base de datos"), HttpStatus.BAD_REQUEST);
        if(!cursoRepository.existsById(inscripcionDto.getCursoId()))
            return new ResponseEntity(new Mensaje("El curso debe existir en la base de datos"), HttpStatus.BAD_REQUEST);
        UsuarioEntity alumno = usuarioRepository.getOne(inscripcionDto.getUsuarioId());
        CursoEntity curso = cursoRepository.getOne(inscripcionDto.getCursoId());
        if(curso.getPrecio() == 0) {
            InscripcionEntity inscripcionEntity = inscripcionMapper.mapInscripcionDtoToInscripcionEntity(inscripcionDto);
            inscripcionEntity.setCursoInscripcion(curso);
            inscripcionEntity.setUsuarioInscripcion(alumno);
            alumno.getInscripciones().add(inscripcionEntity);
            curso.getInscripciones().add(inscripcionEntity);
            inscripcionRepository.save(inscripcionEntity);
            usuarioRepository.save(alumno);
            cursoRepository.save(curso);
            return new ResponseEntity(new Mensaje("Inscripción creada!"), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(new Mensaje("Pagos no implementados, el curso no es gratis! Inscripción Fallida..."), HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<?> update(InscripcionDto inscripcionDto, Long id) {
        if (!inscripcionRepository.existsById(id))
            return new ResponseEntity(new Mensaje("no existe la inscripción en la base de datos"), HttpStatus.NOT_FOUND);
        InscripcionEntity inscripcionEntity = inscripcionRepository.getOne(id);
        inscripcionEntity.setInscripcionAt(inscripcionDto.getInscripcionAt());
        inscripcionEntity.setUsuarioInscripcion(usuarioRepository.findById(inscripcionDto.getUsuarioId()).orElse(null));
        inscripcionEntity.setCursoInscripcion(cursoRepository.findById(inscripcionDto.getCursoId()).orElse(null));
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

