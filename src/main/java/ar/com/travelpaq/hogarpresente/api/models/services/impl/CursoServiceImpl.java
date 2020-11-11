package ar.com.travelpaq.hogarpresente.api.models.services.impl;
import ar.com.travelpaq.hogarpresente.api.models.domain.Curso;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import ar.com.travelpaq.hogarpresente.api.models.mapper.CursoMapper;
import ar.com.travelpaq.hogarpresente.api.models.repository.ICursoRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> findById(Long id) {

        Map<String, Object> response = new HashMap<>();

        CursoEntity cursoEntity = null;

        try{
            cursoEntity = cursoRepository.findById(id).orElse(null);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        }

        if (cursoEntity == null){
            response.put("mensaje", "El Curso ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CursoEntity>(cursoEntity, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean findById2(Long id) {
        if(cursoRepository.findById(id) != null){
            return true;
        }
        else return false;
    }

    @Override
    @Transactional
    public ResponseEntity<?> create(Curso curso) {
        Map<String, Object> response = new HashMap<>();

        CursoEntity cursoEntityNew = null;

        CursoEntity cursoEntity = cursoMapper.mapCursoToCursoEntity(curso);

        try{
            cursoEntityNew = cursoRepository.save(cursoEntity);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El curso fue creado con exito!");
        response.put("curso", cursoEntityNew);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(Curso curso, Long id) {
        Map<String, Object> response = new HashMap<>();

        CursoEntity cursoActual = cursoRepository.findById(id).orElse(null);

        CursoEntity cursoUpdate = cursoMapper.mapCursoToCursoEntity(curso);

        CursoEntity cursoFinal = null;
        if (cursoActual == null){
            response.put("mensaje", "Error: no se pudo editar, el Curso ID".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        try{
            cursoActual.setNombre(cursoUpdate.getNombre());
            cursoActual.setDescripcion(cursoUpdate.getDescripcion());
            cursoActual.setEnabled(cursoUpdate.getEnabled());
            cursoActual.setHoras(cursoUpdate.getHoras());
            cursoActual.setPrecio(cursoUpdate.getPrecio());
            cursoActual.setCapacitador(cursoUpdate.getCapacitador());
            //cursoActual.setInscripciones(cursoUpdate.getInscripciones());
            cursoActual.setUnidades(cursoUpdate.getUnidades());

            cursoFinal = cursoRepository.save(cursoActual);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar el curso en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El Curso ha sido actualizado con exito! ");
        response.put("alumno", cursoFinal);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(Long id) {
        Map<String,Object> response = new HashMap<>();

        try{
            cursoRepository.deleteById(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar el curso de la basa de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El Curso fue eliminado con éxito! ");
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }
}
