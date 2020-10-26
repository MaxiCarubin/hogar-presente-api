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
    @OneToMany(fetch = FetchType.LAZY,targetEntity = InscripcionEntity.class, cascade = CascadeType.ALL)
    private List<InscripcionEntity> inscripciones;

    public Alumno convertToAlumno(){

        Alumno alumno = new Alumno();
        alumno.setNombre(nombre);
        alumno.setApellido(apellido);
        alumno.setCorreo(correo);
        alumno.setClave(clave);
        alumno.setFoto(foto);

        List<Inscripcion> inscripcioesDominio = new ArrayList<>();

        List<InscripcionEntity> inscripcionesEntity = new ArrayList<>();

        inscripciones.forEach(inscripcionEntity -> inscripcionesEntity.add(inscripcionEntity));

        inscripcionesEntity.forEach(inscripcionEntity -> inscripcioesDominio.add(inscripcionEntity.convertToInscripcion()));

        alumno.setInscripciones(inscripcioesDominio);

        return alumno;
    }
}
