package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.dto.Mensaje;
import ar.com.travelpaq.hogarpresente.api.models.dto.MostrarEvaluacionRendidaDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.NuevaEvaluacionRendidaDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.EvaluacionEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.EvaluacionRendidaEntity;
import ar.com.travelpaq.hogarpresente.api.models.enums.Resultado;
import ar.com.travelpaq.hogarpresente.api.models.mapper.EvaluacionRendidaMapper;
import ar.com.travelpaq.hogarpresente.api.models.repository.IEvaluacionRendidaRepository;
import ar.com.travelpaq.hogarpresente.api.models.repository.IEvaluacionReposiroty;
import ar.com.travelpaq.hogarpresente.api.models.services.IEvaluacionRendidaService;
import ar.com.travelpaq.hogarpresente.api.security.entity.UsuarioEntity;
import ar.com.travelpaq.hogarpresente.api.security.repository.IUsuarioRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluacionRendidaServiceImpl implements IEvaluacionRendidaService {

    @Autowired
    private IEvaluacionRendidaRepository evaluacionRendidaRepository;

    @Autowired
    private IEvaluacionReposiroty evaluacionReposiroty;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private EvaluacionRendidaMapper evaluacionRendidaMapper;

    @Override
    public ResponseEntity<?> findAll() {
        List<EvaluacionRendidaEntity> evaluacionesRendidasEntities = evaluacionRendidaRepository.findByOrderById();
        List<MostrarEvaluacionRendidaDto> evaluacionesRendidasDtosDtos = new ArrayList<>();
        for (EvaluacionRendidaEntity evaluacionEntity : evaluacionesRendidasEntities){
            MostrarEvaluacionRendidaDto evaluacionRendidaDto = evaluacionRendidaMapper.mapEvaluacionRendidaEntityToMostrarEvaluacionRendidaDto(evaluacionEntity);
            evaluacionesRendidasDtosDtos.add(evaluacionRendidaDto);
        }
        return new ResponseEntity(evaluacionesRendidasDtosDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(NuevaEvaluacionRendidaDto evaluacionRendidaDto) {
        if (!evaluacionReposiroty.existsById(evaluacionRendidaDto.getEvaluacionId()))
            return new ResponseEntity(new Mensaje("La evaluacion perteneciente a la unidad no existe"), HttpStatus.NOT_FOUND);
        if (!usuarioRepository.existsById(evaluacionRendidaDto.getAlumnoId()))
            return new ResponseEntity(new Mensaje("El usuario que rinde no existe"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(evaluacionRendidaDto.getUrlAlumno()))
            return new ResponseEntity(new Mensaje("La evaluacion no puede estar vacia"), HttpStatus.BAD_REQUEST);
        EvaluacionEntity evaluacionEntitySave = evaluacionReposiroty.getOne(evaluacionRendidaDto.getEvaluacionId());
        EvaluacionEntity evaluacionEntityGet = evaluacionReposiroty.findById(evaluacionRendidaDto.getEvaluacionId()).get();
        UsuarioEntity alumnoEntityGet = usuarioRepository.findById(evaluacionRendidaDto.getAlumnoId()).get();
        if(evaluacionRendidaRepository.existsByAlumno(alumnoEntityGet) && evaluacionRendidaRepository.existsByEvaluacion(evaluacionEntityGet))
            return new ResponseEntity(new Mensaje("Ya se registro el alumno en la evaluacion!"), HttpStatus.BAD_REQUEST);
        EvaluacionRendidaEntity evaluacionRendidaEntity = new EvaluacionRendidaEntity();
        evaluacionRendidaEntity.setUrlAlumno(evaluacionRendidaDto.getUrlAlumno());
        evaluacionRendidaEntity.setNota(0);
        evaluacionRendidaEntity.setResultado(Resultado.PENDIENTE);
        evaluacionRendidaEntity.setAlumno(alumnoEntityGet);
        evaluacionRendidaEntity.setEvaluacion(evaluacionEntityGet);
        evaluacionEntitySave.getEvaluacionesRendidas().add(evaluacionRendidaEntity);
        evaluacionRendidaRepository.save(evaluacionRendidaEntity);
        evaluacionReposiroty.save(evaluacionEntitySave);
        return new ResponseEntity(new Mensaje("Evaluacion rendida añadida!"), HttpStatus.OK);
    }



    @Override
    public ResponseEntity<?> findById(Long id) {
        if (!evaluacionRendidaRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe la evaluacion rendida en la base de datos"), HttpStatus.NOT_FOUND);
        EvaluacionRendidaEntity evaluacionRendidaEntity = evaluacionRendidaRepository.findById(id).get();
        MostrarEvaluacionRendidaDto evaluacionRendidaDto = evaluacionRendidaMapper.mapEvaluacionRendidaEntityToMostrarEvaluacionRendidaDto(evaluacionRendidaEntity);
        return new ResponseEntity(evaluacionRendidaDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (!evaluacionRendidaRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        EvaluacionRendidaEntity evaluacionRendidaEntity = evaluacionRendidaRepository.getOne(id);
        evaluacionRendidaRepository.deleteById(id);
        return new ResponseEntity(new Mensaje("Evaluacion Rendida Eliminada!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateCorreccion(Integer nota, Long id) {
        if (!evaluacionRendidaRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        EvaluacionRendidaEntity evaluacionRendidaEntity = evaluacionRendidaRepository.getOne(id);
        evaluacionRendidaEntity.setNota(nota);
        if (nota >= 6 )
            evaluacionRendidaEntity.setResultado(Resultado.APROBADO);
        else
            evaluacionRendidaEntity.setResultado(Resultado.REPROBADO);
        evaluacionRendidaRepository.save(evaluacionRendidaEntity);
        return new ResponseEntity(new Mensaje("Correccion Realizada!"), HttpStatus.OK);
    }
}
