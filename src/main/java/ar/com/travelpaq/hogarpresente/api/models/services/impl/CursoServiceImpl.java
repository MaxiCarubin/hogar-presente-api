package ar.com.travelpaq.hogarpresente.api.models.services.impl;
import ar.com.travelpaq.hogarpresente.api.models.domain.Curso;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import ar.com.travelpaq.hogarpresente.api.models.mapper.CursoMapper;
import ar.com.travelpaq.hogarpresente.api.models.repository.ICursoRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CursoServiceImpl implements ICursoService {

    @Autowired
    ICursoRepository cursoRepository;

    @Autowired
    CursoMapper cursoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAll() {
        List<CursoEntity> cursoEntities = cursoRepository.findAll();
        List<Curso> cursos = new ArrayList<>();

        cursoEntities.forEach(cursoEntity -> cursos.add(cursoMapper.mapCursoEntityToCurso(cursoEntity)));

        return cursos;
    }

    @Override
    @Transactional(readOnly = true)
    public Curso findByNombre(String nombre) {
        CursoEntity cursoEntity = cursoRepository.findByNombre(nombre);

        Curso curso = new Curso();

        curso = cursoMapper.mapCursoEntityToCurso(cursoEntity);

        return curso;
    }

    @Override
    @Transactional
    public Curso create(Curso curso) {
        CursoEntity cursoEntity = cursoMapper.mapCursoToCursoEntity(curso);

        cursoEntity =cursoRepository.save(cursoEntity);

        return curso;
    }

    @Override
    @Transactional
    public Curso update(Curso curso, long id) {
        CursoEntity cursoActual = cursoRepository.findById(id).orElse(null);
        CursoEntity cursoUpdate = null;

        if (cursoActual==null){
            return null;
        }
        else{
            cursoActual.setNombre(curso.getNombre());
            cursoActual.setDescripcion(curso.getDescripcion());
            cursoActual.setCapacitador(curso.getCapacitador());
            cursoActual.setHoras(curso.getHoras());
            cursoActual.setPrecio(curso.getPrecio());
            cursoActual.setInscripciones(curso.getInscripciones());
            cursoActual.setUnidades(curso.getUnidades());

            cursoUpdate = cursoRepository.save(cursoActual);

            return cursoMapper.mapCursoEntityToCurso(cursoUpdate);
        }
    }

    @Override
    @Transactional
    public void delete(long id) {
        CursoEntity cursoEntity = cursoRepository.findById(id).orElse(null);
        if (cursoEntity == null){
            return;
        }
        else{
            cursoRepository.deleteById(id);
        }
    }
}
