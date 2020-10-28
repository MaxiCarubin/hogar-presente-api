package ar.com.travelpaq.hogarpresente.api.models.entity;
import ar.com.travelpaq.hogarpresente.api.models.domain.Alumno;
import ar.com.travelpaq.hogarpresente.api.models.domain.Inscripcion;
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
@Table(name = "alumnos")
public class AlumnoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nombre",nullable = false)
    private String nombre;
    @Column(name = "apellido",nullable = false)
    private String apellido;
    @Column(name = "correo",nullable = false, unique = true)
    private String correo;
    @Column(name = "clave",nullable = false)
    private String clave;
    @Column(name = "foto")
    private String foto;
    @OneToMany(targetEntity = InscripcionEntity.class)
    private List<InscripcionEntity> inscripciones;

    public Alumno convertToAlumno(AlumnoEntity alumnoEntity ){

        Alumno alumno = new Alumno();
        alumno.setNombre(alumnoEntity.getNombre());
        alumno.setApellido(alumnoEntity.getApellido());
        alumno.setCorreo(alumnoEntity.getCorreo());
        alumno.setClave(alumnoEntity.getClave());
        alumno.setFoto(alumnoEntity.getFoto());

        List<Inscripcion> inscripcioesDominio = new ArrayList<>();

        List<InscripcionEntity> inscripcionesEntity = inscripciones;

        inscripcionesEntity.forEach(inscripcionEntity -> inscripcioesDominio.add(inscripcionEntity.convertToInscripcion(inscripcionEntity)));

        alumno.setInscripciones(inscripcioesDominio);

        return alumno;
    }
}
