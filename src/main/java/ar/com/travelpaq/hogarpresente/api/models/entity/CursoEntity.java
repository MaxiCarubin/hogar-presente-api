package ar.com.travelpaq.hogarpresente.api.models.entity;

import ar.com.travelpaq.hogarpresente.api.models.domain.Curso;
import ar.com.travelpaq.hogarpresente.api.models.domain.Inscripcion;
import ar.com.travelpaq.hogarpresente.api.models.domain.Unidad;
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
@Table(name = "Cursos")
public class CursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nombre;
    private String descripcion;
    private String capacitador;
    private double precio;
    private float horas;
    @OneToMany(fetch = FetchType.LAZY,targetEntity = InscripcionEntity.class, cascade = CascadeType.ALL)
    private List<InscripcionEntity> inscripciones;
    @OneToMany(fetch = FetchType.LAZY,targetEntity = UnidadEntity.class, cascade = CascadeType.ALL)
    private List<UnidadEntity> unidades;

    public Curso convertToCurso(){
        Curso curso = new Curso();
        curso.setId(id);
        curso.setNombre(nombre);
        curso.setDescripcion(descripcion);
        curso.setCapacitador(capacitador);
        curso.setPrecio(precio);
        curso.setHoras(horas);

        List<Unidad> unidaddesDominio = new ArrayList<>();

        List<UnidadEntity> unidadesEntity = new ArrayList<>();

        unidades.forEach(unidadEntity -> unidadesEntity.add(unidadEntity));

        unidadesEntity.forEach(unidadEntity -> unidaddesDominio.add(unidadEntity.convertToUnidad()));

        curso.setUnidades(unidaddesDominio);

        List<Inscripcion> inscripcioesDominio = new ArrayList<>();

        List<InscripcionEntity> inscripcionesEntity = new ArrayList<>();

        inscripciones.forEach(inscripcionEntity -> inscripcionesEntity.add(inscripcionEntity));

        inscripcionesEntity.forEach(inscripcionEntity -> inscripcioesDominio.add(inscripcionEntity.convertToInscripcion()));

        curso.setInscripciones(inscripcioesDominio);

        return curso;
    }

}
