package ar.com.travelpaq.hogarpresente.api.models.services.impl;

import ar.com.travelpaq.hogarpresente.api.models.dto.CursoDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.Mensaje;
import ar.com.travelpaq.hogarpresente.api.models.dto.UnidadDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import ar.com.travelpaq.hogarpresente.api.models.mapper.CursoMapper;
import ar.com.travelpaq.hogarpresente.api.models.repository.ICursoRepository;
import ar.com.travelpaq.hogarpresente.api.models.services.ICursoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
public class CursoServiceImpl implements ICursoService {

    @Autowired
    ICursoRepository cursoRepository;

    @Autowired
    CursoMapper cursoMapper;

    @Override
    public ResponseEntity<List<CursoDto>> findAll() {
        List<CursoEntity> cursoEntities = cursoRepository.findAll();
        List<CursoDto> cursoDtos = new ArrayList<>();

        cursoEntities.forEach(cursoEntity -> cursoDtos.add(cursoMapper.mapCursoEntityToCurso(cursoEntity)));

        return new ResponseEntity(cursoDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (!cursoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        CursoEntity cursoEntity = cursoRepository.getOne(id);
        return new ResponseEntity(cursoEntity, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean findById2(Long id) {
        if(cursoRepository.findById(id) != null){
            return true;
        }
        else return false;
    }

    @Override
    public ResponseEntity<?> create(CursoDto cursoDto) {
        if(StringUtils.isBlank(cursoDto.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(cursoDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        if(cursoDto.getPrecio() < 0)
            return new ResponseEntity(new Mensaje("El precio no puede ser negativo"), HttpStatus.BAD_REQUEST);
        if(cursoDto.getHoras() == null)
            return new ResponseEntity(new Mensaje("No puede contener 0 horas el curso"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(cursoDto.getCapacitador()))
            return new ResponseEntity(new Mensaje("El capacitador es obligatorio"), HttpStatus.BAD_REQUEST);
        CursoEntity cursoEntity = cursoMapper.mapCursoToCursoEntity(cursoDto);
        cursoRepository.save(cursoEntity);
        return new ResponseEntity(new Mensaje("Curso creado!"), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(CursoDto cursoDto, Long id) {
        if (!cursoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("no existe el curso en la base de datos"), HttpStatus.NOT_FOUND);
        CursoEntity cursoEntity = cursoRepository.getOne(id);
        cursoEntity.setNombre(cursoDto.getNombre());
        cursoEntity.setDescripcion(cursoDto.getDescripcion());
        cursoEntity.setCapacitador(cursoDto.getCapacitador());
        cursoEntity.setHoras(cursoDto.getHoras());
        cursoEntity.setPrecio(cursoDto.getPrecio());
        List<UnidadEntity> unidadesEntity = new ArrayList<>();
        List<UnidadDto> unidadesDominio = cursoDto.getUnidades();
        unidadesDominio.forEach(unidad -> unidadesEntity.add(unidad.convertToUnidadEntity(unidad)));
        cursoEntity.setUnidades(unidadesEntity);

        cursoRepository.save(cursoEntity);
        return new ResponseEntity(new Mensaje("Alumno Actualizado!"), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(Long id) {
        if (!cursoRepository.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        cursoRepository.deleteById(id);
        return new ResponseEntity(new Mensaje("Alumno Eliminado!"), HttpStatus.OK);
    }
}
