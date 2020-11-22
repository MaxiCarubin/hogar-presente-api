package ar.com.travelpaq.hogarpresente.api.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDto {

    private long id;

    private String nombre;

    private String descripcion;

    private String capacitador;

    private float precio;

    private int horas;

//    private Boolean enabled;
//
//    private Boolean terminado;

    private String imagen;

//    private Set<InscripcionDto> inscripciones;
//
//    private List<UnidadDto> unidades;



}
