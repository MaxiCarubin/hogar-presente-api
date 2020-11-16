package ar.com.travelpaq.hogarpresente.api.models.dto;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDto {

    private long id;

    private String nombre;

    private String descripcion;

    private String capacitador;

    private float precio;

    private Time horas;

    private Boolean enabled;

    private Boolean terminado;
    //imagen

    private List<UnidadDto> unidades;

    public CursoEntity convertToCursoEntity(CursoDto cursoDto){
        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setId(cursoDto.getId());
        cursoEntity.setNombre(cursoDto.getNombre());
        cursoEntity.setDescripcion(cursoDto.getDescripcion());
        cursoEntity.setPrecio(cursoDto.getPrecio());
        cursoEntity.setCapacitador(cursoDto.getCapacitador());
        cursoEntity.setHoras(cursoDto.getHoras());
        cursoEntity.setEnabled(cursoDto.getEnabled());

        /*
        Set<InscripcionEntity> inscripcionesEntity = new HashSet<>();
        Set<Inscripcion> inscripcioesDominio = inscripciones;
        inscripcioesDominio.forEach(inscripcion -> inscripcionesEntity.add(inscripcion.convertToInscripcionEntity(inscripcion)));
        cursoEntity.setInscripciones(inscripcionesEntity);
        */

        List<UnidadEntity> unidadesEntity = new ArrayList<>();
        List<UnidadDto> unidadesDominio = unidades;
        unidadesDominio.forEach(unidad -> unidadesEntity.add(unidad.convertToUnidadEntity(unidad)));
        cursoEntity.setUnidades(unidadesEntity);

        return cursoEntity;

    }
}
