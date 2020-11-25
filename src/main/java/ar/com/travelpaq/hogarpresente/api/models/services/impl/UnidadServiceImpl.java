package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.dto.Mensaje;
import ar.com.travelpaq.hogarpresente.api.models.dto.UnidadDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.ContenidoEntity;
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
import java.util.ArrayList;
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
    private IContenidoRepository tareaRepository;

    @Override
    public ResponseEntity<List<UnidadDto>> findAll() {
        List<UnidadEntity> unidadEntities = unidadRepository.findAll();
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
        if (!unidadDto.getContenidosId().isEmpty()){
            for (long contenidoId : unidadDto.getContenidosId()){
                if (!tareaRepository.existsById(contenidoId)){
                    return new ResponseEntity(new Mensaje("La tarea ID: " + contenidoId + " no existe en la base de datos"), HttpStatus.NOT_FOUND);
                }
            }
            List<ContenidoEntity> tareas = new ArrayList<>();
            for(long contenidoId : unidadDto.getContenidosId()){
                ContenidoEntity contenidoEntity = tareaRepository.getOne(contenidoId);
                tareas.add(contenidoEntity);
            }
            unidadEntity.setContenidos(tareas);
        }
        unidadRepository.save(unidadEntity);
        CursoEntity cursoEntity = cursoRepository.getOne(unidadDto.getCursoId());
        cursoEntity.getUnidades().add(unidadEntity);
        cursoRepository.save(cursoEntity);
//      unidadEntity.setCurso(cursoRepository.findById(unidadDto.getCursoId()).orElse(null));
        return new ResponseEntity(new Mensaje("Unidad creada!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(UnidadDto unidadDto, Long id) {
        if (!unidadRepository.existsById(id))
            return new ResponseEntity(new Mensaje("no existe la unidad en la base de datos"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(unidadDto.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(unidadDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);

        UnidadEntity unidadEntity = unidadRepository.getOne(id);
        unidadEntity.setNombre(unidadDto.getNombre());
        unidadEntity.setDescripcion(unidadDto.getDescripcion());

        if (!unidadDto.getContenidosId().isEmpty()){
            for (long contenidoId : unidadDto.getContenidosId()){
                if (!tareaRepository.existsById(contenidoId)){
                    return new ResponseEntity(new Mensaje("La tarea ID: " + contenidoId + " no existe en la base de datos"), HttpStatus.NOT_FOUND);
                }
            }
            List<ContenidoEntity> contenidos = new ArrayList<>();
            for(long contenidoId : unidadDto.getContenidosId()){
                ContenidoEntity contenidoEntity = tareaRepository.getOne(contenidoId);
                contenidos.add(contenidoEntity);
            }
            unidadEntity.setContenidos(contenidos);
        }

        unidadRepository.save(unidadEntity);
        return new ResponseEntity(new Mensaje("Unidad Actualizada!"), HttpStatus.OK);

//        unidadEntity.setCurso(cursoRepository.findById(unidadDto.getCursoId()).orElse(null));
//        List<TareaEntity> tareasEntity = new ArrayList<>();
//        List<TareaDto> tareasDto = unidadDto.getTareas();
//        for (TareaDto tareaDto : tareasDto) {
//            tareasEntity.add(tareaMapper.mapTareaEntityToTarea(tareaDto));
//        }
//        unidadEntity.setTareas(tareasEntity);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (!unidadRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        unidadRepository.deleteById(id);
        return new ResponseEntity(new Mensaje("Unidad Eliminada!"), HttpStatus.OK);
    }

//    @Override
//    public ResponseEntity<?> findByCursoId(Long id) {
//        if(!cursoRepository.existsById(id))
//            return new ResponseEntity(new Mensaje("El curso debe existir en la base de datos"), HttpStatus.NOT_FOUND);
//        CursoEntity cursoEntity = cursoRepository.findById(id).orElse(null);
//        List<UnidadEntity> unidadEntities = unidadRepository.findAllByCurso(cursoEntity);
//        return new ResponseEntity(unidadEntities, HttpStatus.OK);
//    }
}
