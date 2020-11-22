package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.dto.Mensaje;
import ar.com.travelpaq.hogarpresente.api.models.dto.TareaDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.TareaEntity;
import ar.com.travelpaq.hogarpresente.api.models.mapper.TareaMapper;
import ar.com.travelpaq.hogarpresente.api.models.mapper.UnidadMapper;
import ar.com.travelpaq.hogarpresente.api.models.repository.ITareaRepository;
import ar.com.travelpaq.hogarpresente.api.models.repository.IUnidadRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.ITareaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TareaServiceImpl implements ITareaService {

    @Autowired
    private ITareaRepository tareaRepository;

    @Autowired
    private TareaMapper tareaMapper;

    @Autowired
    private UnidadMapper unidadMapper;

    @Autowired
    private IUnidadRepository unidadRepository;

    @Override
    public ResponseEntity<List<TareaDto>> findAll() {
        List<TareaEntity> tareaEntities = tareaRepository.findAll();
        return new ResponseEntity(tareaEntities, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (!tareaRepository.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        TareaEntity tareaEntity = tareaRepository.getOne(id);
        return new ResponseEntity(tareaEntity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(TareaDto tareaDto) {
        if(StringUtils.isBlank(tareaDto.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(tareaDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        if(!unidadRepository.existsById(tareaDto.getUnidadId())){
            return new ResponseEntity(new Mensaje("La unidad debe existir en la base de datos"), HttpStatus.BAD_REQUEST);
        }
        TareaEntity tareaEntity = tareaMapper.mapTareaEntityToTarea(tareaDto);
        tareaEntity.setUnidad(unidadRepository.findById(tareaDto.getUnidadId()).orElse(null));
        tareaRepository.save(tareaEntity);
        return new ResponseEntity(new Mensaje("Tarea Creada!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(TareaDto tareaDto, Long id) {
        if (!tareaRepository.existsById(id))
            return new ResponseEntity(new Mensaje("no existe la tarea en la base de datos"), HttpStatus.NOT_FOUND);
        TareaEntity tareaEntity = tareaRepository.getOne(id);
        tareaEntity.setNombre(tareaDto.getNombre());
        tareaEntity.setDescripcion(tareaDto.getDescripcion());
        tareaEntity.setDocumento(tareaDto.getDocumento());
        tareaEntity.setUnidad(unidadRepository.findById(tareaDto.getUnidadId()).orElse(null));
        tareaRepository.save(tareaEntity);
        return new ResponseEntity(new Mensaje("Unidad Actualizada!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (!tareaRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        tareaRepository.deleteById(id);
        return new ResponseEntity(new Mensaje("Unidad Eliminada!"), HttpStatus.OK);
    }
}
