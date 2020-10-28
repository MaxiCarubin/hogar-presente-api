package ar.com.travelpaq.hogarpresente.api.models.entity;

import ar.com.travelpaq.hogarpresente.api.models.domain.Tarea;
import ar.com.travelpaq.hogarpresente.api.models.domain.Unidad;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "unidades")
public class UnidadEntity {
    @Id
    private String nombre;
    private String descripcion;
    @OneToMany(targetEntity = TareaEntity.class)
    private List<TareaEntity> tareas;

    public Unidad convertToUnidad(UnidadEntity unidadEntity) {
        Unidad unidad = new Unidad();
        unidad.setNombre(unidadEntity.getNombre());
        unidad.setDescripcion(unidadEntity.getDescripcion());

        List<Tarea> tareasDominio = new ArrayList<>();

        List<TareaEntity> tareasEntity = tareas;

        tareasEntity.forEach(tareaEntity -> tareasDominio.add(tareaEntity.convertToTarea(tareaEntity)));

        unidad.setTareas(tareasDominio);

        return unidad;
    }
}
