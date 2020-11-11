package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.dto.Mensaje;
import ar.com.travelpaq.hogarpresente.api.models.dto.UnidadDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.TareaEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import ar.com.travelpaq.hogarpresente.api.models.mapper.UnidadMapper;
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
    IUnidadRepository unidadRepository;

    @Autowired
    UnidadMapper unidadMapper;

    @Override
    public ResponseEntity<List<UnidadDto>> findAll() {
        List<UnidadEntity> unidadEntities = unidadRepository.findAll();
        List<UnidadDto> unidadDtos = new ArrayList<>();

        unidadEntities.forEach(unidadEntity -> unidadDtos.add(unidadMapper.mapUnidadEntityToUnidad(unidadEntity)));

        return new ResponseEntity(unidadDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (!unidadRepository.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        UnidadEntity unidadEntity = unidadRepository.getOne(id);
        return new ResponseEntity(unidadEntity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(UnidadDto unidadDto) {
        if(StringUtils.isBlank(unidadDto.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(unidadDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        UnidadEntity unidadEntity = unidadMapper.mapUnidadToUnidadEntity(unidadDto);
        unidadRepository.save(unidadEntity);
        return new ResponseEntity(new Mensaje("Unidad creada!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(UnidadDto unidadDto, Long id) {
        if (!unidadRepository.existsById(id))
            return new ResponseEntity(new Mensaje("no existe la unidad en la base de datos"), HttpStatus.NOT_FOUND);
        UnidadEntity unidadEntity = unidadRepository.getOne(id);
        unidadEntity.setNombre(unidadDto.getNombre());
        unidadEntity.setDescripcion(unidadDto.getDescripcion());
        List<TareaEntity> tareaEntities = new ArrayList<>();
        unidadDto.getTareaDtos().forEach(tarea -> tareaEntities.add(tarea.convertToTareaEntity(tarea)));
        unidadEntity.setTareas(tareaEntities);
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
}
