package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.domain.Alumno;
import ar.com.travelpaq.hogarpresente.api.models.domain.Inscripcion;
import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.InscripcionEntity;
import ar.com.travelpaq.hogarpresente.api.models.mapper.AlumnoMapper;
import ar.com.travelpaq.hogarpresente.api.models.repository.IAlumnoRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.IAlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AlumnoServiceImpl implements IAlumnoService {

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

        Alumno alumno = new Alumno();

        alumno = alumnoMapper.mapAlumnoEntityToAlumno(alumnoEntity);

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
}
