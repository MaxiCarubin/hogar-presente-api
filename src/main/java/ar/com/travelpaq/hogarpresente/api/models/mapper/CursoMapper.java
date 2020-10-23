package ar.com.travelpaq.hogarpresente.api.models.mapper;

import ar.com.travelpaq.hogarpresente.api.models.domain.Curso;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {
    public Curso mapCursoEntityToCurso(CursoEntity cursoEntity){
        Curso curso = new Curso();
        curso.setId(cursoEntity.getId());
        curso.setNombre(cursoEntity.getNombre());
        curso.setDescripcion(cursoEntity.getDescripcion());
        curso.setCapacitador(cursoEntity.getCapacitador());
        curso.setCantidad_unidades(cursoEntity.getCantidad_unidades());
        curso.setHoras(cursoEntity.getHoras());
        curso.setPrecio(cursoEntity.getPrecio());
        curso.setInscripciones(cursoEntity.getInscripciones());
        curso.setUnidades(cursoEntity.getUnidades());
        return curso;
    }
    public CursoEntity mapCursoToCursoEntity(Curso curso){
        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setId(curso.getId());
        cursoEntity.setNombre(curso.getNombre());
        cursoEntity.setDescripcion(curso.getDescripcion());
        cursoEntity.setCapacitador(curso.getCapacitador());
        cursoEntity.setCantidad_unidades(curso.getCantidad_unidades());
        cursoEntity.setHoras(curso.getHoras());
        cursoEntity.setPrecio(curso.getPrecio());
        cursoEntity.setInscripciones(curso.getInscripciones());
        cursoEntity.setUnidades(curso.getUnidades());
        return cursoEntity;
    }
}
