package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.domain.Inscripcion;
import ar.com.travelpaq.hogarpresente.api.models.entity.InscripcionEntity;
import ar.com.travelpaq.hogarpresente.api.models.mapper.InscripcionMapper;
import ar.com.travelpaq.hogarpresente.api.models.repository.IInscripcionRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.IInscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InscripcionServiceImpl implements IInscripcionService {

    @Autowired
    private IInscripcionRepository inscripcionRepository;

    @Autowired
    private InscripcionMapper inscripcionMapper;

    @Override
    public List<Inscripcion> findAll() {
        List<InscripcionEntity> inscripcionEntities = inscripcionRepository.findAll();
        List<Inscripcion> inscripciones = new ArrayList<>();

        inscripcionEntities.forEach(inscripcionEntity -> inscripciones.add(inscripcionMapper.mapInscripcionEntityToInscripcion(inscripcionEntity)));

        return inscripciones;
    }

    @Override
    public ResponseEntity<?> create(Inscripcion inscripcion) {
        Map<String, Object> response = new HashMap<>();

        InscripcionEntity inscripcionEntityNew = null;

        InscripcionEntity inscripcionEntity = inscripcionMapper.mapInscripcionToInscripcionEntity(inscripcion);

        try{
            inscripcionEntityNew = inscripcionRepository.save(inscripcionEntity);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La inscripcion fue creada con éxito!");
        response.put("inscripcion", inscripcionEntityNew);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> delete(Date fecha) {
        Map<String,Object> response = new HashMap<>();

        try{
            inscripcionRepository.deleteById(fecha);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar la inscripcion de la basa de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La inscripcion fue eliminado con éxito! ");
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }
}

