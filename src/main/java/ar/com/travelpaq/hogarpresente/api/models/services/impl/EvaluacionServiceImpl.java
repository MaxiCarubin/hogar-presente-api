package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.dto.Mensaje;
import ar.com.travelpaq.hogarpresente.api.models.dto.MostrarEvaluacionDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.NuevaEvaluacionDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.EvaluacionEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import ar.com.travelpaq.hogarpresente.api.models.mapper.EvaluacionMapper;
import ar.com.travelpaq.hogarpresente.api.models.repository.IEvaluacionReposiroty;
import ar.com.travelpaq.hogarpresente.api.models.repository.IUnidadRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.IEvaluacionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluacionServiceImpl implements IEvaluacionService {

    @Autowired
    private IEvaluacionReposiroty evaluacionReposiroty;

    @Autowired
    private EvaluacionMapper evaluacionMapper;

    @Autowired
    private IUnidadRepository unidadRepository;

    @Override
    public ResponseEntity<?> findAll() {
        List<EvaluacionEntity> evaluacionesEntities = evaluacionReposiroty.findByOrderById();
        List<MostrarEvaluacionDto> evaluacionesDtos = new ArrayList<>();
        for (EvaluacionEntity evaluacionEntity : evaluacionesEntities){
            MostrarEvaluacionDto evaluacionDto = evaluacionMapper.mapMostrarEvaluacionDtoToEvaluacionEntity(evaluacionEntity);
            evaluacionesDtos.add(evaluacionDto);
        }
        return new ResponseEntity(evaluacionesDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (!evaluacionReposiroty.existsById(id))
            return new ResponseEntity(new Mensaje("No existe la evaluacion en la base de datos"), HttpStatus.NOT_FOUND);
        EvaluacionEntity evaluacionEntity = evaluacionReposiroty.findById(id).get();
        MostrarEvaluacionDto evaluacionDto = evaluacionMapper.mapMostrarEvaluacionDtoToEvaluacionEntity(evaluacionEntity);
        return new ResponseEntity(evaluacionDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (!evaluacionReposiroty.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        EvaluacionEntity evaluacionEntity = evaluacionReposiroty.getOne(id);
        evaluacionReposiroty.deleteById(id);
        return new ResponseEntity(new Mensaje("Evaluacion Eliminada!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(NuevaEvaluacionDto evaluacion) {
        if(StringUtils.isBlank(evaluacion.getNombre()))
            return new ResponseEntity(new Mensaje("El Nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(evaluacion.getUrlCapacitador()))
            return new ResponseEntity(new Mensaje("El Documento es obligatorio"), HttpStatus.BAD_REQUEST);
        if(!unidadRepository.existsById(evaluacion.getUnidadId()))
            return new ResponseEntity(new Mensaje("La unidad no esta en la base de datos"), HttpStatus.NOT_FOUND);
        UnidadEntity unidadSave = unidadRepository.getOne(evaluacion.getUnidadId());
        UnidadEntity unidadGet = unidadRepository.findById(evaluacion.getUnidadId()).get();
        EvaluacionEntity evaluacionEntity = evaluacionMapper.mapNuevaEvaluacionDtoToEvaluacionEntity(evaluacion);
        evaluacionEntity.setEvaluacionesRendidas(null);
        evaluacionEntity.setUnidad(unidadGet);
        unidadSave.setEvaluacion(evaluacionEntity);
        evaluacionReposiroty.save(evaluacionEntity);
        unidadRepository.save(unidadSave);
        return new ResponseEntity(new Mensaje("Evaluacion creada!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateNombre(String nombre, Long id) {
        if (!evaluacionReposiroty.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        EvaluacionEntity evaluacionEntity = evaluacionReposiroty.getOne(id);
        evaluacionEntity.setNombre(nombre);
        evaluacionReposiroty.save(evaluacionEntity);
        return new ResponseEntity(new Mensaje("Evaluacion Actualizado!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateUrlCapacitador(String urlCapacitador, Long id) {
        if (!evaluacionReposiroty.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        EvaluacionEntity evaluacionEntity = evaluacionReposiroty.getOne(id);
        evaluacionEntity.setUrlCapacitador(urlCapacitador);
        evaluacionReposiroty.save(evaluacionEntity);
        return new ResponseEntity(new Mensaje("Evaluacion Actualizado!"), HttpStatus.OK);
    }

}
