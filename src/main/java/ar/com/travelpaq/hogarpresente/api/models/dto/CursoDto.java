package ar.com.travelpaq.hogarpresente.api.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDto {
    private long id;
    private String nombre;
    private String descripcion;
    private String capacitador;
    private float precio;
    private String categoria;
    private String imagen;
    private boolean habilitado;
}
