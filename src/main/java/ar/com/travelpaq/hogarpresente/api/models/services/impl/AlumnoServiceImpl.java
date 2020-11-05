package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.domain.Alumno;
import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import ar.com.travelpaq.hogarpresente.api.models.mapper.AlumnoMapper;
import ar.com.travelpaq.hogarpresente.api.models.repository.IAlumnoRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.IAlumnoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AlumnoServiceImpl implements IAlumnoService, UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(AlumnoServiceImpl.class);

    @Autowired
    private IAlumnoRepository alumnoRepository;

    @Autowired
    private AlumnoMapper alumnoMapper;

    @Override
    @Transactional(readOnly = true)
    public AlumnoEntity findByCorreo(String correo) {
        return alumnoRepository.findByCorreo(correo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findAll() {
        List<AlumnoEntity> alumnoEntities = alumnoRepository.findAll();
        List<Alumno> alumnos = new ArrayList<>();

        alumnoEntities.forEach(alumnoEntity -> alumnos.add(alumnoMapper.mapAlumnoEntityToAlumno(alumnoEntity)));

        return alumnos;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long id) {

        Map<String, Object> response = new HashMap<>();

        AlumnoEntity alumnoEntity = null;
        Alumno alumno = null;

        try{
            alumnoEntity = alumnoRepository.findById(id).orElse(null);
            if (alumnoEntity == null){
                alumno = null;
            }
            else
            {
                alumno = alumnoMapper.mapAlumnoEntityToAlumno(alumnoEntity);
            }
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        }

        if (alumno == null){
            response.put("mensaje", "El Cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> create(Alumno alumno) {

        Map<String, Object> response = new HashMap<>();

        AlumnoEntity alumnoEntityNew = null;

        AlumnoEntity alumnoEntity = alumnoMapper.mapAlumnoToAlumnoEntity(alumno);

        try{
            alumnoEntityNew = alumnoRepository.save(alumnoEntity);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente fue creado con exito!");
        response.put("cliente", alumnoEntityNew);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(Alumno alumno, Long id) {

        Map<String, Object> response = new HashMap<>();

        AlumnoEntity alumnoActual = alumnoRepository.findById(id).orElse(null);

        AlumnoEntity alumnoUpdate = alumnoMapper.mapAlumnoToAlumnoEntity(alumno);

        AlumnoEntity alumnoFinal = null;
        if (alumnoActual == null){
            response.put("mensaje", "Error: no se pudo editar, el Alumno ID".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        try{
            alumnoActual.setNombre(alumnoUpdate.getNombre());
            alumnoActual.setApellido(alumnoUpdate.getApellido());
            alumnoActual.setCorreo(alumnoUpdate.getCorreo());
            alumnoActual.setClave(alumnoUpdate.getClave());
            alumnoActual.setFoto(alumnoUpdate.getFoto());
            alumnoActual.setInscripciones(alumnoUpdate.getInscripciones());
            alumnoActual.setRoles(alumnoUpdate.getRoles());
            alumnoActual.setEnabled(alumnoUpdate.getEnabled());

            /*
            Set<InscripcionEntity> inscripcionesEntity = new HashSet<>();

            Set<Inscripcion> inscripciones = alumno.getInscripciones();

            inscripciones.forEach(inscripcion -> inscripcionesEntity.add(inscripcion.convertToInscripcionEntity(inscripcion)));

            alumnoActual.setInscripciones(inscripcionesEntity);
            alumnoRepository.save(alumnoActual);

            return alumno;
             */

            alumnoFinal = alumnoRepository.save(alumnoActual);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar el cliente en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El Alumno ha sido actualizado con exito! ");
        response.put("alumno", alumnoFinal);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(Long id) {

        Map<String,Object> response = new HashMap<>();

        try{
            alumnoRepository.deleteById(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar el alumno de la basa de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente fue eliminado con éxito! ");
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        AlumnoEntity alumnoEntity = alumnoRepository.findByCorreo(correo);

        if (alumnoEntity == null)
        {
            logger.error("Error en el Login: no existe el usuario con correo '"+ correo + "' en el sistema! ");
            throw new UsernameNotFoundException("Error en el Login: no existe el usuario con correo '"+ correo + "' en el sistema! ");
        }
        List<GrantedAuthority> authorities = alumnoEntity.getRoles()
                .stream()
                .map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getNombre()))
                .peek(authority -> logger.info("Role: " + authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(alumnoEntity.getCorreo(), alumnoEntity.getClave(), alumnoEntity.getEnabled(), true, true, true, authorities);
    }
}
