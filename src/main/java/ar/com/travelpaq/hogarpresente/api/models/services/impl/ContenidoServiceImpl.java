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
    private IContenidoRepository contenidoRepository;

    @Autowired
    private ContenidoMapper contenidoMapper;

    @Autowired
    private UnidadMapper unidadMapper;

    @Autowired
    private IUnidadRepository unidadRepository;

    @Override
    public ResponseEntity<List<ContenidoDto>> findAll() {
        List<ContenidoEntity> tareaEntities = contenidoRepository.findAll();
        return new ResponseEntity(tareaEntities, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (!contenidoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el contenido en la base de datos"), HttpStatus.NOT_FOUND);
        ContenidoEntity contenidoEntity = contenidoRepository.findById(id).get();
        return new ResponseEntity(contenidoEntity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(ContenidoDto contenidoDto) {
        if(StringUtils.isBlank(contenidoDto.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(contenidoDto.getDenominacion()))
            return new ResponseEntity(new Mensaje("La denominacion es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(contenidoDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(contenidoDto.getDocumento()))
            return new ResponseEntity(new Mensaje("Documentacion vacia!"), HttpStatus.BAD_REQUEST);
        if(!unidadRepository.existsById(contenidoDto.getUnidadId()))
            return new ResponseEntity(new Mensaje("La unidad debe existir en la base de datos"), HttpStatus.BAD_REQUEST);
        ContenidoEntity contenidoEntity = contenidoMapper.mapTareaEntityToTarea(contenidoDto);
        contenidoRepository.save(contenidoEntity);
        UnidadEntity unidadEntity = unidadRepository.getOne(contenidoDto.getUnidadId());
        unidadEntity.getContenidos().add(contenidoEntity);
        unidadRepository.save(unidadEntity);
        return new ResponseEntity(new Mensaje("Contenido creado para la unidad ID: " + contenidoDto.getUnidadId() + "  !"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(ContenidoDto contenidoDto, Long id) {
        if (!contenidoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el contenido en la base de datos"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(contenidoDto.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(contenidoDto.getDenominacion()))
            return new ResponseEntity(new Mensaje("La denominacion es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(contenidoDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(contenidoDto.getDocumento()))
            return new ResponseEntity(new Mensaje("Documentacion vacia!"), HttpStatus.BAD_REQUEST);
        if(!unidadRepository.existsById(contenidoDto.getUnidadId()))
            return new ResponseEntity(new Mensaje("No existe unidad asociado al contenido en la base de datos"), HttpStatus.BAD_REQUEST);
        ContenidoEntity contenidoEntity = contenidoRepository.getOne(id);
        contenidoEntity.setNombre(contenidoDto.getNombre());
        contenidoEntity.setDenominacion(contenidoDto.getDenominacion());
        contenidoEntity.setDescripcion(contenidoDto.getDescripcion());
        contenidoEntity.setDocumento(contenidoDto.getDocumento());
        contenidoRepository.save(contenidoEntity);
        return new ResponseEntity(new Mensaje("Contenido Actualizado!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (!contenidoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el contenido en la base de datos"), HttpStatus.NOT_FOUND);
        contenidoRepository.deleteById(id);
        return new ResponseEntity(new Mensaje("Contenido Eliminado!"), HttpStatus.OK);
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
