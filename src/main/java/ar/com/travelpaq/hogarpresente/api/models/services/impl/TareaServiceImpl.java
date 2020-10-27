package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.domain.Tarea;
import ar.com.travelpaq.hogarpresente.api.models.domain.Unidad;
import ar.com.travelpaq.hogarpresente.api.models.entity.TareaEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import ar.com.travelpaq.hogarpresente.api.models.mapper.TareaMapper;
import ar.com.travelpaq.hogarpresente.api.models.repository.ITareaRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.ITareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TareaServiceImpl implements ITareaService {
    @Autowired
    ITareaRepository tareaRepository;

    @Autowired
    TareaMapper tareaMapper;

    @Override
    public List<Tarea> findAll() {
        List<TareaEntity> tareaEntities = tareaRepository.findAll();
        List<Tarea> tareas = new ArrayList<>();

        tareaEntities.forEach(tareaEntity -> tareas.add(tareaMapper.mapTareaToTareaEntity(tareaEntity)));

        return tareas;
    }

    @Override
    public Tarea findById(String nombre) {
        TareaEntity tareaEntity = tareaRepository.findById(nombre).orElse(null);

        Tarea tarea = new Tarea();

        tarea = tareaMapper.mapTareaToTareaEntity(tareaEntity);

        return tarea;
    }

    @Override
    public Tarea create(Tarea tarea) {
        TareaEntity tareaEntity = tareaMapper.mapTareaEntityToTarea(tarea);

        tareaEntity =tareaRepository.save(tareaEntity);

        return tarea;
    }

    @Override
    public Tarea update(Tarea tarea, String nombre) {
        TareaEntity tareaAcutal = tareaRepository.findById(nombre).orElse(null);
        Tarea tareaUpdate = null;

        if (tareaAcutal==null){
            return null;
        }
        else{
            tareaAcutal = tareaMapper.mapTareaEntityToTarea(tarea);
            tareaUpdate = tareaMapper.mapTareaToTareaEntity(tareaAcutal);
            return tareaUpdate;
        }
    }

    @Override
    public void delete(String nombre) {
        tareaRepository.deleteById(nombre);
    }
}
