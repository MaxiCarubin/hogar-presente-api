package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.dto.Mensaje;
import ar.com.travelpaq.hogarpresente.api.models.dto.ContenidoDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.ContenidoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import ar.com.travelpaq.hogarpresente.api.models.mapper.ContenidoMapper;
import ar.com.travelpaq.hogarpresente.api.models.mapper.UnidadMapper;
import ar.com.travelpaq.hogarpresente.api.models.repository.IContenidoRepository;
import ar.com.travelpaq.hogarpresente.api.models.repository.IUnidadRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.IContenidoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContenidoServiceImpl implements IContenidoService {

    @Autowired
    private IContenidoRepository tareaRepository;

    @Autowired
    private ContenidoMapper contenidoMapper;

    @Autowired
    private UnidadMapper unidadMapper;

    @Autowired
    private IUnidadRepository unidadRepository;

    @Override
    public ResponseEntity<List<ContenidoDto>> findAll() {
        List<ContenidoEntity> tareaEntities = tareaRepository.findAll();
        return new ResponseEntity(tareaEntities, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (!tareaRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el contenido en la base de datos"), HttpStatus.NOT_FOUND);
        ContenidoEntity contenidoEntity = tareaRepository.findById(id).get();
        return new ResponseEntity(contenidoEntity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(ContenidoDto contenidoDto) {
        if(StringUtils.isBlank(contenidoDto.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(contenidoDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
//        if(!unidadRepository.existsById(tareaDto.getUnidadId()))
//            return new ResponseEntity(new Mensaje("La unidad debe existir en la base de datos"), HttpStatus.BAD_REQUEST);
        ContenidoEntity contenidoEntity = contenidoMapper.mapTareaEntityToTarea(contenidoDto);
//        tareaEntity.setUnidad(unidadRepository.findById(tareaDto.getUnidadId()).orElse(null));
        tareaRepository.save(contenidoEntity);
        UnidadEntity unidadEntity = unidadRepository.getOne(contenidoDto.getUnidadId());
        unidadEntity.getContenidos().add(contenidoEntity);
        unidadRepository.save(unidadEntity);
        return new ResponseEntity(new Mensaje("Tarea Creada!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(ContenidoDto contenidoDto, Long id) {
        if (!tareaRepository.existsById(id))
            return new ResponseEntity(new Mensaje("no existe la tarea en la base de datos"), HttpStatus.NOT_FOUND);
        ContenidoEntity contenidoEntity = tareaRepository.getOne(id);
        contenidoEntity.setNombre(contenidoDto.getNombre());
        contenidoEntity.setDescripcion(contenidoDto.getDescripcion());
        contenidoEntity.setDocumento(contenidoDto.getDocumento());
//        tareaEntity.setUnidad(unidadRepository.findById(tareaDto.getUnidadId()).orElse(null));
        tareaRepository.save(contenidoEntity);
        return new ResponseEntity(new Mensaje("Unidad Actualizada!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (!tareaRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        tareaRepository.deleteById(id);
        return new ResponseEntity(new Mensaje("Unidad Eliminada!"), HttpStatus.OK);
    }

//    @Override
//    public ResponseEntity<?> findByUnidadId(Long id) {
//        if (!unidadRepository.existsById(id))
//            return new ResponseEntity(new Mensaje("La unidad debe existir en la base de datos"), HttpStatus.NOT_FOUND);
//        UnidadEntity unidadEntity = unidadRepository.findById(id).orElse(null);
//        List<TareaEntity> tareaEntities = tareaRepository.findAllByUnidad(unidadEntity);
//        return new ResponseEntity(tareaEntities, HttpStatus.OK);
//    }
}
