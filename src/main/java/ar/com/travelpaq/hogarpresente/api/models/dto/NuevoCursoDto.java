package ar.com.travelpaq.hogarpresente.api.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NuevoCursoDto {
    private long id;
    private String titulo;
    private String subtitulo;
    private String descripcion;
    private float precio;
    private String categoria;
    private long usuarioId;
}
