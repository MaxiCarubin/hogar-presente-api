package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.domain.Alumno;
import ar.com.travelpaq.hogarpresente.api.models.domain.Inscripcion;
import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.InscripcionEntity;
import ar.com.travelpaq.hogarpresente.api.models.mapper.AlumnoMapper;
import ar.com.travelpaq.hogarpresente.api.models.repository.IAlumnoRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.IAlumnoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public List<Alumno> findAll() {
        List<AlumnoEntity> alumnoEntities = alumnoRepository.findAll();
        List<Alumno> alumnos = new ArrayList<>();

        alumnoEntities.forEach(alumnoEntity -> alumnos.add(alumnoMapper.mapAlumnoEntityToAlumno(alumnoEntity)));

        return alumnos;
    }

    @Override
    @Transactional(readOnly = true)
    public Alumno findById(Long id) {
        AlumnoEntity alumnoEntity = alumnoRepository.findById(id).orElse(null);

        Alumno alumno = alumnoMapper.mapAlumnoEntityToAlumno(alumnoEntity);

        return alumno;
    }

    @Override
    @Transactional
    public Alumno create(Alumno alumno) {
        AlumnoEntity alumnoEntity = new AlumnoEntity();

        alumnoEntity = alumnoMapper.mapAlumnoToAlumnoEntity(alumno);

        alumnoRepository.save(alumnoEntity);

        return alumno;
    }

    @Override
    @Transactional
    public Alumno update(Alumno alumno, Long id) {

        AlumnoEntity alumnoActual = alumnoRepository.findById(id).orElse(null);

        alumnoActual.setNombre(alumno.getNombre());
        alumnoActual.setApellido(alumno.getApellido());
        alumnoActual.setCorreo(alumno.getCorreo());
        alumnoActual.setClave(alumno.getClave());
        alumnoActual.setFoto(alumno.getFoto());

        Set<InscripcionEntity> inscripcionesEntity = new HashSet<>();

        Set<Inscripcion> inscripciones = alumno.getInscripciones();

        inscripciones.forEach(inscripcion -> inscripcionesEntity.add(inscripcion.convertToInscripcionEntity(inscripcion)));

        alumnoActual.setInscripciones(inscripcionesEntity);

        alumnoRepository.save(alumnoActual);

        return alumno;

    }

    @Override
    @Transactional
    public void delete(Long id) {
        AlumnoEntity alumnoEntity = new AlumnoEntity();
        alumnoEntity = alumnoRepository.findById(id).orElse(null);
        if (alumnoEntity == null){
            return;
        }
        else{
            String nombreFotoAnterior = alumnoEntity.getFoto();
            if (nombreFotoAnterior != null && nombreFotoAnterior.length()>0){
                Path rutaFotoAnterior = Paths.get("uploads").toAbsolutePath();
                File archivoFotoAnterior = rutaFotoAnterior.toFile();
                if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()){
                    archivoFotoAnterior.delete();
                }
            }
            alumnoRepository.deleteById(id);
        }
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
