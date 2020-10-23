package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.domain.Alumno;
import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
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
import java.util.List;

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
    public Alumno findById(long id) {
        AlumnoEntity alumnoEntity = alumnoRepository.findById(id).orElse(null);

        Alumno alumno = new Alumno();

        alumno = alumnoMapper.mapAlumnoEntityToAlumno(alumnoEntity);

        return alumno;
    }

    @Override
    @Transactional
    public Alumno create(Alumno alumno) {
        AlumnoEntity alumnoEntity = alumnoMapper.mapAlumnoToAlumnoEntity(alumno);

        alumnoEntity =alumnoRepository.save(alumnoEntity);

        return alumno;
    }

    @Override
    public Alumno update(Alumno alumno, long id) {

        AlumnoEntity alumnoActual = alumnoRepository.findById(id).orElse(null);
        AlumnoEntity alumnoUpdate = null;

        if (alumnoActual==null){
            return null;
        }
        else{
            alumnoActual.setApellido(alumno.getApellido());
            alumnoActual.setClave(alumno.getClave());
            alumnoActual.setCorreo(alumno.getCorreo());
            alumnoActual.setNombre(alumno.getNombre());
            alumnoActual.setFoto(alumno.getFoto());

            alumnoUpdate = alumnoRepository.save(alumnoActual);

            return alumnoMapper.mapAlumnoEntityToAlumno(alumnoUpdate);
        }
    }

    @Override
    @Transactional
    public void delete(long id) {
        AlumnoEntity alumnoEntity = alumnoRepository.findById(id).orElse(null);
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
