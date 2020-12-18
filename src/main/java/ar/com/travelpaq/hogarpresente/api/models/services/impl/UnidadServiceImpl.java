package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.dto.Mensaje;
import ar.com.travelpaq.hogarpresente.api.models.dto.UnidadDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import ar.com.travelpaq.hogarpresente.api.models.mapper.CursoMapper;
import ar.com.travelpaq.hogarpresente.api.models.mapper.ContenidoMapper;
import ar.com.travelpaq.hogarpresente.api.models.mapper.UnidadMapper;
import ar.com.travelpaq.hogarpresente.api.models.repository.ICursoRepository;
import ar.com.travelpaq.hogarpresente.api.models.repository.IContenidoRepository;
import ar.com.travelpaq.hogarpresente.api.models.repository.IUnidadRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.IUnidadService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UnidadServiceImpl implements IUnidadService {

    @Autowired
    private IUnidadRepository unidadRepository;

    @Autowired
    private ICursoRepository cursoRepository;

    @Autowired
    private CursoMapper cursoMapper;

    @Autowired
    private UnidadMapper unidadMapper;

    @Autowired
    private ContenidoMapper contenidoMapper;

    @Autowired
    private IContenidoRepository contenidoRepository;

    @Override
    public ResponseEntity<List<UnidadDto>> findAll() {
        List<UnidadEntity> unidadEntities = unidadRepository.findByOrderById();
        return new ResponseEntity(unidadEntities, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (!unidadRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe la unidad en la base de datos"), HttpStatus.NOT_FOUND);
        UnidadEntity unidadEntity = unidadRepository.findById(id).get();
        return new ResponseEntity(unidadEntity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(UnidadDto unidadDto) {
        if(StringUtils.isBlank(unidadDto.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(unidadDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        if(!cursoRepository.existsById(unidadDto.getCursoId()))
            return new ResponseEntity(new Mensaje("El curso debe existir en la base de datos"), HttpStatus.NOT_FOUND);
        UnidadEntity unidadEntity = unidadMapper.mapUnidadToUnidadEntity(unidadDto);
        CursoEntity cursoEntity = cursoRepository.getOne(unidadDto.getCursoId());
        cursoEntity.getUnidades().add(unidadEntity);
        unidadEntity.setCurso(cursoEntity);
        unidadRepository.save(unidadEntity);
        cursoRepository.save(cursoEntity);
        return new ResponseEntity(new Mensaje("Unidad creada!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(UnidadDto unidadDto, Long id) {
        if (!unidadRepository.existsById(id))
            return new ResponseEntity(new Mensaje("no existe la unidad en la base de datos"), HttpStatus.NOT_FOUND);
        if(!cursoRepository.existsById(unidadDto.getCursoId()))
            return new ResponseEntity(new Mensaje("No existe curso asociado a la unidad en la base de datos"), HttpStatus.NOT_FOUND);
        UnidadEntity unidadEntity = unidadRepository.getOne(id);
        unidadEntity.setNombre(unidadDto.getNombre());
        unidadEntity.setDescripcion(unidadDto.getDescripcion());
        unidadRepository.save(unidadEntity);
        return new ResponseEntity(new Mensaje("Unidad Actualizada!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (!unidadRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        unidadRepository.deleteById(id);
        return new ResponseEntity(new Mensaje("Unidad Eliminada!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createNull(long cursoId) {
        UnidadEntity unidad = new UnidadEntity();
        if(!cursoRepository.existsById(cursoId))
            return new ResponseEntity(new Mensaje("No existe curso asociado a la unidad en la base de datos"), HttpStatus.NOT_FOUND);
        CursoEntity cursoSave = cursoRepository.getOne(cursoId);
        CursoEntity cursoGet = cursoRepository.findById(cursoId).get();
        if(cursoGet.getUnidades().isEmpty()){
            unidad.setNombre("Unidad 1");
            unidad.setDescripcion(null);
            unidad.setNumeroUnidad(1);
            unidad.setContenidos(null);
        }
        if (!cursoGet.getUnidades().isEmpty())
        {
            int cont = 1;
            for (int i = 0; i<cursoGet.getUnidades().size(); i++){
                cont++;
            }
            unidad.setNombre("Unidad " + cont);
            unidad.setDescripcion(null);
            unidad.setNumeroUnidad(cont);
            unidad.setContenidos(null);
        }
        unidad.setCurso(cursoGet);
        cursoSave.getUnidades().add(unidad);
        unidadRepository.save(unidad);
        cursoRepository.save(cursoSave);
        return new ResponseEntity(new Mensaje("Unidad creada!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateDescripcion(String descripcion, Long id) {
        if (!unidadRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        UnidadEntity unidadEntity = unidadRepository.getOne(id);
        unidadEntity.setDescripcion(descripcion);
        unidadRepository.save(unidadEntity);
        return new ResponseEntity(new Mensaje("Unidad Actualizada!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateNombre(String nombre, Long id) {
        if (!unidadRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        UnidadEntity unidadEntity = unidadRepository.getOne(id);
        unidadEntity.setNombre(nombre);
        unidadRepository.save(unidadEntity);
        return new ResponseEntity(new Mensaje("Unidad Actualizada!"), HttpStatus.OK);
    }
}
