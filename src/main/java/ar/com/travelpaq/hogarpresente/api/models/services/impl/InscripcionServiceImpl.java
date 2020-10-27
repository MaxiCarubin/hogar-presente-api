package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.domain.Inscripcion;
import ar.com.travelpaq.hogarpresente.api.models.entity.InscripcionEntity;
import ar.com.travelpaq.hogarpresente.api.models.mapper.InscripcionMapper;
import ar.com.travelpaq.hogarpresente.api.models.repository.IInscripcionRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.IInscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public Inscripcion create(Inscripcion inscripcion) {
        InscripcionEntity inscripcionEntity = inscripcionMapper.mapInscripcionToInscripcionEntity(inscripcion);

        inscripcionEntity = inscripcionRepository.save(inscripcionEntity);

        return inscripcion;
    }

    @Override
    public void delete(Date fecha) {
        inscripcionRepository.deleteById(fecha);
    }
}

