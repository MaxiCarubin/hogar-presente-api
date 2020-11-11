package ar.com.travelpaq.hogarpresente.api.models.entity;

import ar.com.travelpaq.hogarpresente.api.models.dto.TareaDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.UnidadDto;
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
    private long id;

    @Column(nullable = false, length = 75)
    private String nombre;

    @Column(nullable = false, length = 75)
    private String descripcion;

    @OneToMany(targetEntity = TareaEntity.class)
    private List<TareaEntity> tareas;

    public UnidadDto convertToUnidad(UnidadEntity unidadEntity) {
        UnidadDto unidadDto = new UnidadDto();
        unidadDto.setId(unidadEntity.getId());
        unidadDto.setNombre(unidadEntity.getNombre());
        unidadDto.setDescripcion(unidadEntity.getDescripcion());

        List<TareaDto> tareasDominio = new ArrayList<>();

        List<TareaEntity> tareasEntity = tareas;

        tareasEntity.forEach(tareaEntity -> tareasDominio.add(tareaEntity.convertToTarea(tareaEntity)));

        unidadDto.setTareaDtos(tareasDominio);

        return unidadDto;
    }
}
