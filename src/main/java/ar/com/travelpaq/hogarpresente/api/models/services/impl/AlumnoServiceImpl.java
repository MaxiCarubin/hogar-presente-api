package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import ar.com.travelpaq.hogarpresente.api.models.repository.IAlumnoRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.IAlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlumnoServiceImpl implements IAlumnoService {

    @Autowired
    private IAlumnoRepository alumnoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AlumnoEntity> findAll() {
        return (List<AlumnoEntity>) alumnoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public AlumnoEntity findById(long id) {
        return alumnoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public AlumnoEntity save(AlumnoEntity alumnoEntity) {
        return alumnoRepository.save(alumnoEntity);
    }

    @Override
    @Transactional
    public void delete(long id) {
        alumnoRepository.deleteById(id);
    }
}
