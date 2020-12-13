package ar.com.travelpaq.hogarpresente.api.security.entity;

import ar.com.travelpaq.hogarpresente.api.cloudinary.entity.ImagenEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import ar.com.travelpaq.hogarpresente.api.models.entity.InscripcionEntity;
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

    @Column(nullable = false, length = 45)
    private String nombre;

    @Column(nullable = false, length = 45)
    private String apellido;

    private int edad;

    @Email
    @Column(unique = true, nullable = false)
    private String correo;

    @Column(nullable = false, length = 400)
    private String clave;

//    @Column(length = 400)
//    private String foto;

    @ManyToOne
    @JoinColumn(name = "imagen_id")
    private ImagenEntity imagen;

    @Column(length = 45)
    private String estudio;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<CursoEntity> cursos;

    @OneToMany(mappedBy = "usuarioInscripcion", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<InscripcionEntity> inscripciones;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","role_id"})})
    private Set<RoleEntity> roles;

    public UsuarioEntity(String nombre, String apellido, String correo, String clave, int edad, ImagenEntity imagen, String estudio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.clave = clave;
        this.edad = edad;
        this.imagen = imagen;
        this.estudio = estudio;
    }

}
