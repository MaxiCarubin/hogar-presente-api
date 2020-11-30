package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.dto.*;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import ar.com.travelpaq.hogarpresente.api.models.mapper.ContenidoMapper;
import ar.com.travelpaq.hogarpresente.api.models.mapper.CursoMapper;
import ar.com.travelpaq.hogarpresente.api.models.mapper.InscripcionMapper;
import ar.com.travelpaq.hogarpresente.api.models.mapper.UnidadMapper;
import ar.com.travelpaq.hogarpresente.api.models.repository.IContenidoRepository;
import ar.com.travelpaq.hogarpresente.api.models.repository.ICursoRepository;
import ar.com.travelpaq.hogarpresente.api.models.repository.IUnidadRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.ICursoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CursoServiceImpl implements ICursoService {

    @Autowired
    private ICursoRepository cursoRepository;

    @Autowired
    private CursoMapper cursoMapper;

    @Autowired
    private InscripcionMapper inscripcionMapper;

    @Autowired
    private UnidadMapper unidadMapper;

    @Autowired
    private IUnidadRepository unidadRepository;

    @Autowired
    private ContenidoMapper contenidoMapper;

    @Autowired
    private IContenidoRepository contenidoRepository;

    @Override
    public ResponseEntity<?> findAll() {
        List<CursoEntity> cursoEntities = cursoRepository.findAll();
        return new ResponseEntity(cursoEntities, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (!cursoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el curso en la base de datos"), HttpStatus.NOT_FOUND);
        CursoEntity cursoEntity = cursoRepository.findById(id).get();
        return new ResponseEntity(cursoEntity, HttpStatus.OK);
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
    public ResponseEntity<?> create(CursoDto newCurso) {
        if(StringUtils.isBlank(newCurso.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(newCurso.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        if(newCurso.getPrecio() < 0)
            return new ResponseEntity(new Mensaje("El precio no puede ser negativo"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(newCurso.getCapacitador()))
            return new ResponseEntity(new Mensaje("El capacitador es obligatorio"), HttpStatus.BAD_REQUEST);
        CursoEntity cursoEntity = cursoMapper.mapCursoToCursoEntity(newCurso);
        cursoEntity.setHabilitado(false);
        cursoRepository.save(cursoEntity);
        return new ResponseEntity(new Mensaje("Curso creado!"), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(CursoDto cursoDto, Long id) {
        if (!cursoRepository.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el curso en la base de datos"), HttpStatus.NOT_FOUND);
        }
        else {
            if (StringUtils.isBlank(cursoDto.getNombre()))
                return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
            if (StringUtils.isBlank(cursoDto.getDescripcion()))
                return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
            if (cursoDto.getPrecio() < 0)
                return new ResponseEntity(new Mensaje("El precio no puede ser negativo"), HttpStatus.BAD_REQUEST);
            if (StringUtils.isBlank(cursoDto.getCapacitador()))
                return new ResponseEntity(new Mensaje("El capacitador es obligatorio"), HttpStatus.BAD_REQUEST);
            CursoEntity cursoEntity = cursoRepository.getOne(id);
            cursoEntity.setNombre(cursoDto.getNombre());
            cursoEntity.setDescripcion(cursoDto.getDescripcion());
            cursoEntity.setCapacitador(cursoDto.getCapacitador());
            cursoEntity.setPrecio(cursoDto.getPrecio());
            cursoEntity.setImagen(cursoDto.getImagen());
            cursoEntity.setCategoria(cursoDto.getCategoria());
            cursoEntity.setHabilitado(cursoDto.isHabilitado());
            cursoRepository.save(cursoEntity);
            return new ResponseEntity(new Mensaje("Curso Actualizado!"), HttpStatus.OK);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(Long id) {
        if (!cursoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        cursoRepository.deleteById(id);
        return new ResponseEntity(new Mensaje("Curso Eliminado!"), HttpStatus.OK);
    }
}
