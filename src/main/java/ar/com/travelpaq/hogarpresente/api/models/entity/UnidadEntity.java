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
    @OneToMany(fetch = FetchType.LAZY, targetEntity = TareaEntity.class, cascade = CascadeType.ALL)
    private List<TareaEntity> tareas;

    public Unidad convertToUnidad() {
        Unidad unidad = new Unidad();
        unidad.setNombre(nombre);
        unidad.setDescripcion(descripcion);

        List<Tarea> tareasDominio = new ArrayList<>();

        List<TareaEntity> tareasEntity = new ArrayList<>();

        tareas.forEach(tareaEntity -> tareasEntity.add(tareaEntity));

        tareasEntity.forEach(tareaEntity -> tareasDominio.add(tareaEntity.convertToTarea()));

        unidad.setTareas(tareasDominio);

        return unidad;
    }
}
