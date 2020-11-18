package ar.com.travelpaq.hogarpresente.api.models.entity;
import ar.com.travelpaq.hogarpresente.api.models.dto.AlumnoDto;
import ar.com.travelpaq.hogarpresente.api.security.dto.RoleDto;
import ar.com.travelpaq.hogarpresente.api.security.entity.RoleEntity;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alumnos")
public class AlumnoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String nombre;

    @NotNull
    private String apellido;

    @NotNull
    @Email
    @Column(unique = true)
    private String correo;

    @NotNull
    private String clave;

    private String foto;

    private Boolean enabled;//sacar

    //Edad

    //Estudios

    public AlumnoEntity(String nombre, String apellido, String correo, String clave) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.clave = clave;
    }

    /*
        @OneToMany(fetch = FetchType.LAZY, targetEntity = InscripcionEntity.class, cascade = CascadeType.ALL)
        private Set<InscripcionEntity> inscripciones;
         */
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "alumnos_roles", joinColumns = @JoinColumn(name = "alumno_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"alumno_id","role_id"})})
    private Set<RoleEntity> roles;

    public AlumnoDto convertToAlumno(AlumnoEntity alumnoEntity ){

        AlumnoDto alumnoDto = new AlumnoDto();
        alumnoDto.setId(alumnoEntity.getId());
        alumnoDto.setNombre(alumnoEntity.getNombre());
        alumnoDto.setApellido(alumnoEntity.getApellido());
        alumnoDto.setCorreo(alumnoEntity.getCorreo());
        alumnoDto.setClave(alumnoEntity.getClave());
        alumnoDto.setFoto(alumnoEntity.getFoto());

        Set<RoleDto> rolesDominio = new HashSet<>();
        Set<RoleEntity> rolesEntity = alumnoEntity.getRoles();
        rolesEntity.forEach(roleEntity -> rolesDominio.add(roleEntity.convertToRole(roleEntity)));
        alumnoDto.setRoles(rolesDominio);

        /*
        Set<Inscripcion> inscripcioesDominio = new HashSet<>();
        Set<InscripcionEntity> inscripcionesEntity = inscripciones;
        inscripcionesEntity.forEach(inscripcionEntity -> inscripcioesDominio.add(inscripcionEntity.convertToInscripcion(inscripcionEntity)));
        alumno.setInscripciones(inscripcioesDominio);
        */

        return alumnoDto;
    }
}
