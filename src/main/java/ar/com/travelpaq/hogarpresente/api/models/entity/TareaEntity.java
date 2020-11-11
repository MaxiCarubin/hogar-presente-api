package ar.com.travelpaq.hogarpresente.api.models.entity;

import ar.com.travelpaq.hogarpresente.api.models.domain.Tarea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tareas")
public class TareaEntity {
    @Id
    private long id;

    @Column(nullable = false, length = 60)
    private String nombre;

    @Column(nullable = false, length = 70)
    private String descripcion;

    @Column(nullable = false, length = 60)
    private String documento;

    public Tarea convertToTarea(TareaEntity tareaEntity) {
        Tarea tarea = new Tarea();
        tarea.setId(tareaEntity.getId());
        tarea.setNombre(tareaEntity.getNombre());
        tarea.setDocumento(tareaEntity.getDocumento());
        tarea.setDescripcion(tareaEntity.getDescripcion());
        return tarea;
    }
}
