package ar.com.travelpaq.hogarpresente.api.models.entity;

import ar.com.travelpaq.hogarpresente.api.security.entity.RoleEntity;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.io.Serializable;
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

    private int edad;

    @NotNull
    @Email
    @Column(unique = true)
    private String correo;

    @NotNull
    private String clave;

    private String foto;

    private String estudio;

//    @OneToMany(mappedBy = "alumno_entity", fetch = FetchType.EAGER)
//    private Set<InscripcionEntity> inscripciones;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "alumnos_roles", joinColumns = @JoinColumn(name = "alumno_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"alumno_id","role_id"})})
    private Set<RoleEntity> roles;

    public AlumnoEntity(String nombre, String apellido, String correo, String clave, int edad, String foto, String estudio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.clave = clave;
        this.edad = edad;
        this.foto = foto;
        this.estudio = estudio;
    }

}
