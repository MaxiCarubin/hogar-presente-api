package ar.com.travelpaq.hogarpresente.api.models.services.impl;

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
    public List<CursoEntity> findAll() {
        return (List<CursoEntity>) cursoRepository.findAll();
    }

    @Override
    public CursoEntity findById(long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    @Override
    public CursoEntity save(CursoEntity cursoEntity) {
        return cursoRepository.save(cursoEntity);
    }

    @Override
    public void delete(long id) {
        cursoRepository.deleteById(id);
    }
}
