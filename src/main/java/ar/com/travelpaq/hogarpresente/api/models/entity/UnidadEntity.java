package ar.com.travelpaq.hogarpresente.api.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    private List tareas;
}
