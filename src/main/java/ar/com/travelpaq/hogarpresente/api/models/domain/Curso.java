package ar.com.travelpaq.hogarpresente.api.models.domain;
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

    private int cantidad_unidades;

    private double precio;

    private float horas;

    private List<Inscripcion> inscripciones;

    private List<Unidad> unidades;

}
