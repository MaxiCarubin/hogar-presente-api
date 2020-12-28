package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.dto.MostrarEvaluacionDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.NuevaEvaluacionDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.EvaluacionEntity;
import org.springframework.stereotype.Component;

@Component
public class EvaluacionMapper {

    public MostrarEvaluacionDto mapMostrarEvaluacionDtoToEvaluacionEntity(EvaluacionEntity evaluacionEntity) {
        MostrarEvaluacionDto evaluacionDto = new MostrarEvaluacionDto();
        evaluacionDto.setId(evaluacionEntity.getId());
        evaluacionDto.setNombre(evaluacionEntity.getNombre());
        evaluacionDto.setUrlCapacitador(evaluacionEntity.getUrlCapacitador());
        evaluacionDto.setUnidadId(evaluacionEntity.getUnidad().getId());
        evaluacionDto.setEvaluacionesRendidas(evaluacionEntity.getEvaluacionesRendidas());
        return evaluacionDto;
    }

    public EvaluacionEntity mapNuevaEvaluacionDtoToEvaluacionEntity(NuevaEvaluacionDto evaluacion) {
        EvaluacionEntity evaluacionEntity = new EvaluacionEntity();
        evaluacionEntity.setNombre(evaluacion.getNombre());
        evaluacionEntity.setUrlCapacitador(evaluacion.getUrlCapacitador());
        return evaluacionEntity;
    }
}
