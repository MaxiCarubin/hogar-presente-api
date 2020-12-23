package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.dto.Mensaje;
import ar.com.travelpaq.hogarpresente.api.models.dto.ContenidoDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.NuevoContenidoDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.ContenidoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
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
        List<ContenidoEntity> tareaEntities = contenidoRepository.findByOrderById();
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
            return new ResponseEntity(new Mensaje("El Nombre es obligatorio!"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(contenidoDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripción es obligatoria!"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(contenidoDto.getDocumento()))
            return new ResponseEntity(new Mensaje("La documentación es obligatoria!"), HttpStatus.BAD_REQUEST);
        if(!unidadRepository.existsById(contenidoDto.getUnidadId()))
            return new ResponseEntity(new Mensaje("La unidad a la que se le asigna el contenido, debe existir en la base de datos!"), HttpStatus.NOT_FOUND);
        ContenidoEntity contenidoEntity = contenidoMapper.mapTareaEntityToTarea(contenidoDto);
        UnidadEntity unidadEntity = unidadRepository.getOne(contenidoDto.getUnidadId());
        unidadEntity.getContenidos().add(contenidoEntity);
        contenidoEntity.setUnidad(unidadEntity);
        contenidoEntity.setTerminado(false);
        contenidoRepository.save(contenidoEntity);
        unidadRepository.save(unidadEntity);
        return new ResponseEntity(new Mensaje("Contenido creado para la unidad ID: " + contenidoDto.getUnidadId() + " !"), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> update(ContenidoDto contenidoDto, Long id) {
        if (!contenidoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el contenido en la base de datos"), HttpStatus.NOT_FOUND);
        if(!unidadRepository.existsById(contenidoDto.getUnidadId()))
            return new ResponseEntity(new Mensaje("No existe unidad asociado al contenido en la base de datos"), HttpStatus.BAD_REQUEST);
        ContenidoEntity contenidoEntity = contenidoRepository.getOne(id);
        contenidoEntity.setNombre(contenidoDto.getNombre());
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

    @Override
    public ResponseEntity<?> createNull(NuevoContenidoDto contenidoDto) {
        ContenidoEntity contenido = new ContenidoEntity();
        if(!unidadRepository.existsById(contenidoDto.getUnidadId()))
            return new ResponseEntity(new Mensaje("No existe la unidad asociado al contenido en la base de datos"), HttpStatus.NOT_FOUND);
        UnidadEntity unidadSave = unidadRepository.getOne(contenidoDto.getUnidadId());
        UnidadEntity unidadGet = unidadRepository.findById(contenidoDto.getUnidadId()).get();
        contenido.setNombre(contenidoDto.getNombre());
        contenido.setDescripcion(null);
        contenido.setDocumento(null);
        contenido.setTerminado(false);
        contenido.setUnidad(unidadGet);
        unidadSave.getContenidos().add(contenido);
        contenidoRepository.save(contenido);
        unidadRepository.save(unidadSave);
        return new ResponseEntity(new Mensaje("Contenido creado!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateNombre(String nombre, Long id) {
        if (!contenidoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        ContenidoEntity contenidoEntity = contenidoRepository.getOne(id);
        contenidoEntity.setNombre(nombre);
        contenidoRepository.save(contenidoEntity);
        return new ResponseEntity(new Mensaje("Curso Actualizado!"), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> updateDescripcion(String descripcion, Long id) {
        if (!contenidoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        ContenidoEntity contenidoEntity = contenidoRepository.getOne(id);
        contenidoEntity.setDescripcion(descripcion);
        contenidoRepository.save(contenidoEntity);
        return new ResponseEntity(new Mensaje("Contenido Actualizado!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> terminarOnOffCurso(Long id) {
        if (!contenidoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        ContenidoEntity contenidoEntity = contenidoRepository.getOne(id);
        Mensaje mensaje = new Mensaje();
        if(contenidoEntity.isTerminado()) {
            contenidoEntity.setTerminado(false);
            mensaje.setMensaje("Contenid No Terminado!");
        }
        else{
            contenidoEntity.setTerminado(true);
            mensaje.setMensaje("Contenido Terminado!");
        }
        contenidoRepository.save(contenidoEntity);
        return new ResponseEntity(mensaje, HttpStatus.OK);
    }
}
