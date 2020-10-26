package ar.com.travelpaq.hogarpresente.api.models.domain;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.InscripcionEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {

    private long id;

    private String nombre;

    private String descripcion;

    private String capacitador;

    private double precio;

    private float horas;

    private List<Inscripcion> inscripciones;

    private List<Unidad> unidades;

    public CursoEntity convertToCursoEntity(){
        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setId(id);
        cursoEntity.setNombre(nombre);
        cursoEntity.setDescripcion(descripcion);
        cursoEntity.setPrecio(precio);
        cursoEntity.setCapacitador(capacitador);
        cursoEntity.setHoras(horas);

        List<Inscripcion> inscripcioesDominio = new ArrayList<>();

        List<InscripcionEntity> inscripcionesEntity = new ArrayList<>();

        inscripciones.forEach(inscripcion -> inscripcioesDominio.add(inscripcion));

        inscripcioesDominio.forEach(inscripcion -> inscripcionesEntity.add(inscripcion.convertToInscripcionEntity()));

        cursoEntity.setInscripciones(inscripcionesEntity);

        List<Unidad> unidadesDominio = new ArrayList<>();

        List<UnidadEntity> unidadesEntity = new ArrayList<>();

        unidades.forEach(unidad -> unidadesDominio.add(unidad));

        unidadesDominio.forEach(unidad -> unidadesEntity.add(unidad.convertToUnidadEntity()));

        cursoEntity.setUnidades(unidadesEntity);

        return cursoEntity;

    }
}
