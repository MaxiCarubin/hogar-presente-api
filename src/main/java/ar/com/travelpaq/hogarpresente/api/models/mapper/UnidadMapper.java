package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.dto.UnidadDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnidadMapper {

    @Autowired
    private CursoMapper cursoMapper;

    @Autowired
    private TareaMapper tareaMapper;

    public UnidadDto mapUnidadEntityToUnidad(UnidadEntity unidadEntity){
        UnidadDto unidadDto = new UnidadDto();
        unidadDto.setId(unidadEntity.getId());
        unidadDto.setNombre(unidadEntity.getNombre());
        unidadDto.setDescripcion(unidadEntity.getDescripcion());

//        unidadDto.setCurso(cursoMapper.mapCursoEntityToCurso(unidadEntity.getCurso()));

//        List<TareaDto> tareasDominio = new ArrayList<>();
//        List<TareaEntity> tareasEntity = unidadEntity.getTareas();
//        for (TareaEntity tareaEntity : tareasEntity) {
//            tareasDominio.add(tareaMapper.mapTareaToTareaEntity(tareaEntity));
//        }
//        unidadDto.setTareas(tareasDominio);

        return unidadDto;
    }
    public UnidadEntity mapUnidadToUnidadEntity(UnidadDto unidadDto){
        UnidadEntity unidadEntity = new UnidadEntity();
        unidadEntity.setId(unidadDto.getId());
        unidadEntity.setNombre(unidadDto.getNombre());
        unidadEntity.setDescripcion(unidadDto.getDescripcion());

//        unidadEntity.setCurso(cursoMapper.mapCursoToCursoEntity(unidadDto.getCurso()));

//        List<TareaEntity> tareasEntity = new ArrayList<>();
//        List<TareaDto> tareasDto = unidadDto.getTareas();
//        for (TareaDto tareaDto : tareasDto) {
//            tareasEntity.add(tareaMapper.mapTareaEntityToTarea(tareaDto));
//        }
//        unidadEntity.setTareas(tareasEntity);

        return unidadEntity;
    }
}
