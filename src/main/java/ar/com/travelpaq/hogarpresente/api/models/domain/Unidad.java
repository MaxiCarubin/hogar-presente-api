package ar.com.travelpaq.hogarpresente.api.models.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Unidad {

    private String nombre;

    private String descripcion;

    private List<Tarea> tareas;
}