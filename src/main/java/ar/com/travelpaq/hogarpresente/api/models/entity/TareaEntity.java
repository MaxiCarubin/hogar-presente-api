package ar.com.travelpaq.hogarpresente.api.models.entity;

import ar.com.travelpaq.hogarpresente.api.models.domain.Inscripcion;
import ar.com.travelpaq.hogarpresente.api.models.domain.Tarea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String nombre;
    private String descripcion;
    private String documento;

    public Tarea convertToTarea() {
        Tarea tarea = new Tarea();
        tarea.setNombre(nombre);
        tarea.setDocumento(documento);
        tarea.setDescripcion(descripcion);
        return tarea;
    }
}
