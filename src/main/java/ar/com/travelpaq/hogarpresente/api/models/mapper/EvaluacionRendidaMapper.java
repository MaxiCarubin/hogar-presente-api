package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.dto.MostrarEvaluacionRendidaDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.EvaluacionRendidaEntity;
import org.springframework.stereotype.Component;

@Component
public class EvaluacionRendidaMapper {
    public MostrarEvaluacionRendidaDto mapEvaluacionRendidaEntityToMostrarEvaluacionRendidaDto(EvaluacionRendidaEntity evaluacionRendidaEntity) {
        MostrarEvaluacionRendidaDto evaluacionesRendidasDto = new MostrarEvaluacionRendidaDto();
        evaluacionesRendidasDto.setId(evaluacionRendidaEntity.getId());
        evaluacionesRendidasDto.setUrlAlumno(evaluacionRendidaEntity.getUrlAlumno());
        evaluacionesRendidasDto.setNota(evaluacionRendidaEntity.getNota());
        evaluacionesRendidasDto.setResultado(evaluacionRendidaEntity.getResultado());
        evaluacionesRendidasDto.setAlumnoId(evaluacionRendidaEntity.getAlumno().getId());
        evaluacionesRendidasDto.setEvaluacionId(evaluacionRendidaEntity.getEvaluacion().getId());
        return evaluacionesRendidasDto;
    }
}
