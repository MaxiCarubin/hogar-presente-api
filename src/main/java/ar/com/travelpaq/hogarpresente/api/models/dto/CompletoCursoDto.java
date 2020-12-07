package ar.com.travelpaq.hogarpresente.api.models.dto;

import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompletoCursoDto {
    private long id;
    private String nombre;
    private String descripcion;
    private float precio;
    private String categoria;
    private String imagen;
    private long id_usuario_creador;
    private List<UnidadEntity> unidades;
}
