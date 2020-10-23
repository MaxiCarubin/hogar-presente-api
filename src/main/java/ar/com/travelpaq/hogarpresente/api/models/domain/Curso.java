package ar.com.travelpaq.hogarpresente.api.models.domain;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
        cursoEntity.setInscripciones(inscripciones);
        cursoEntity.setUnidades(unidades);

        return cursoEntity;
    }
}
