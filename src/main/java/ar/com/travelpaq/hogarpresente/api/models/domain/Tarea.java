package ar.com.travelpaq.hogarpresente.api.models.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarea {
    private String nombre;
    private String descripcion;
    private String documento;
}