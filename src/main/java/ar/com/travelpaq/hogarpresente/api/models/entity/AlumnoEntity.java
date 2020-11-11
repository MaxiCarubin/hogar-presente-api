package ar.com.travelpaq.hogarpresente.api.models.entity;
import ar.com.travelpaq.hogarpresente.api.models.dto.AlumnoDto;
import ar.com.travelpaq.hogarpresente.api.models.dto.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alumnos")
public class AlumnoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 45)
    private String nombre;

    @Column(nullable = false, length = 45)
    private String apellido;

    @Column(nullable = false, unique = true, length = 60)
    private String correo;

    @Column(nullable = false, length = 60)
    private String clave;

    private String foto;

    private Boolean enabled;

    /*
    @OneToMany(fetch = FetchType.LAZY, targetEntity = InscripcionEntity.class, cascade = CascadeType.ALL)
    private Set<InscripcionEntity> inscripciones;
     */

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "alumnos_roles", joinColumns = @JoinColumn(name = "alumno_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"alumno_id","role_id"})})
    private List<RoleEntity> roles;

    public AlumnoDto convertToAlumno(AlumnoEntity alumnoEntity ){

        AlumnoDto alumnoDto = new AlumnoDto();
        alumnoDto.setId(alumnoEntity.getId());
        alumnoDto.setNombre(alumnoEntity.getNombre());
        alumnoDto.setApellido(alumnoEntity.getApellido());
        alumnoDto.setCorreo(alumnoEntity.getCorreo());
        alumnoDto.setClave(alumnoEntity.getClave());
        alumnoDto.setFoto(alumnoEntity.getFoto());

        List<RoleDto> rolesDominio = new ArrayList<>();
        List<RoleEntity> rolesEntity = alumnoEntity.getRoles();
        rolesEntity.forEach(roleEntity -> rolesDominio.add(roleEntity.convertToRole(roleEntity)));
        alumnoDto.setRoleDtos(rolesDominio);

        /*
        Set<Inscripcion> inscripcioesDominio = new HashSet<>();
        Set<InscripcionEntity> inscripcionesEntity = inscripciones;
        inscripcionesEntity.forEach(inscripcionEntity -> inscripcioesDominio.add(inscripcionEntity.convertToInscripcion(inscripcionEntity)));
        alumno.setInscripciones(inscripcioesDominio);
        */

        return alumnoDto;
    }
}
