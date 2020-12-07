package ar.com.travelpaq.hogarpresente.api.models.entity;

import ar.com.travelpaq.hogarpresente.api.security.entity.RoleEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class UsuarioEntity implements Serializable {

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<CursoEntity> cursos;

//    @OneToMany(mappedBy = "alumno_entity", fetch = FetchType.EAGER)
//    private Set<InscripcionEntity> inscripciones;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","role_id"})})
    private Set<RoleEntity> roles;

    public UsuarioEntity(String nombre, String apellido, String correo, String clave, int edad, String foto, String estudio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.clave = clave;
        this.edad = edad;
        this.foto = foto;
        this.estudio = estudio;
    }

}
