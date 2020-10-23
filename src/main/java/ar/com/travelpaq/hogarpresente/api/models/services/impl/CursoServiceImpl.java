package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.domain.Curso;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import ar.com.travelpaq.hogarpresente.api.models.repository.ICursoRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements ICursoService {

    @Autowired
    ICursoRepository cursoRepository;

    @Override
    public List<Curso> findAll() {
        return null;
    }

    @Override
    public Curso findById(long id) {
        return null;
    }

    @Override
    public Curso create(Curso curso) {
        return null;
    }

    @Override
    public Curso update(Curso curso, long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
