package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.domain.Unidad;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import ar.com.travelpaq.hogarpresente.api.models.mapper.UnidadMapper;
import ar.com.travelpaq.hogarpresente.api.models.repository.IUnidadRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.IUnidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UnidadServiceImpl implements IUnidadService {
    @Autowired
    IUnidadRepository unidadRepository;

    @Autowired
    UnidadMapper unidadMapper;

    @Override
    public List<Unidad> findAll() {
        List<UnidadEntity> unidadEntities = unidadRepository.findAll();
        List<Unidad> unidades = new ArrayList<>();

        unidadEntities.forEach(unidadEntity -> unidades.add(unidadMapper.mapUnidadEntityToUnidad(unidadEntity)));

        return unidades;
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        Map<String, Object> response = new HashMap<>();

        UnidadEntity unidadEntity = null;

        try{
            unidadEntity = unidadRepository.findById(id).orElse(null);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        }

        if (unidadEntity == null){
            response.put("mensaje", "La Unidad con id ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UnidadEntity>(unidadEntity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(Unidad unidad) {
        Map<String, Object> response = new HashMap<>();

        UnidadEntity unidadEntityNew = null;

        UnidadEntity unidadEntity = unidadMapper.mapUnidadToUnidadEntity(unidad);

        try{
            unidadEntityNew = unidadRepository.save(unidadEntity);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La unidad fue creada con exito!");
        response.put("unidad", unidadEntityNew);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> update(Unidad unidad, Long id) {
        Map<String, Object> response = new HashMap<>();

        UnidadEntity unidadActual = unidadRepository.findById(id).orElse(null);

        UnidadEntity unidadUpdate = unidadMapper.mapUnidadToUnidadEntity(unidad);

        UnidadEntity unidadFinal = null;
        if (unidadActual == null){
            response.put("mensaje", "Error: no se pudo editar, la unidad ID ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        try{
            unidadActual.setNombre(unidadUpdate.getNombre());
            unidadActual.setDescripcion(unidadUpdate.getDescripcion());
            unidadActual.setTareas(unidadUpdate.getTareas());

            unidadFinal = unidadRepository.save(unidadActual);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar la unidad en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La unidad ha sido actualizado con exito! ");
        response.put("unidad", unidadFinal);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Map<String,Object> response = new HashMap<>();

        try{
            unidadRepository.deleteById(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar la unidad de la basa de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La unidad fue eliminada con éxito! ");
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }
}
