package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.domain.Unidad;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import ar.com.travelpaq.hogarpresente.api.models.mapper.UnidadMapper;
import ar.com.travelpaq.hogarpresente.api.models.repository.IUnidadRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.IUnidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public Unidad findById(String nombre) {
        UnidadEntity unidadEntity = unidadRepository.findById(nombre).orElse(null);

        Unidad unidad = new Unidad();

        unidad = unidadMapper.mapUnidadEntityToUnidad(unidadEntity);

        return unidad;
    }

    @Override
    public Unidad create(Unidad unidad) {
        UnidadEntity unidadEntity = unidadMapper.mapUnidadToUnidadEntity(unidad);

        unidadEntity =unidadRepository.save(unidadEntity);

        return unidad;
    }

    @Override
    public Unidad update(Unidad unidad, String nombre) {
        UnidadEntity unidadActual = unidadRepository.findById(nombre).orElse(null);
        Unidad unidadUpdate = null;

        if (unidadActual==null){
            return null;
        }
        else{
            unidadActual = unidadMapper.mapUnidadToUnidadEntity(unidad);
            unidadUpdate = unidadMapper.mapUnidadEntityToUnidad(unidadActual);
            return unidadUpdate;
        }
    }

    @Override
    public void delete(String nombre) {
        unidadRepository.deleteById(nombre);
    }
}
