package ar.com.travelpaq.hogarpresente.api.models.dto;

import ar.com.travelpaq.hogarpresente.api.cloudinary.dto.ImagenDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.UnidadEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MostrarCursoDto {
    private long id;
    private String titulo;
    private String subtitulo;
    private String descripcion;
    private float precio;
    private String categoria;
    private String imagen;
    private boolean habilitado;
    private long id_usuario_creador;
    private List<UnidadEntity> unidades;
}
