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
    private String titulo;
    private String descripcion;
    private String subtitulo;
    private float precio;
    private String categoria;
    private long usuarioId;
}
