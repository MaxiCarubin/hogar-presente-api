package ar.com.travelpaq.hogarpresente.api.security.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPrincipal implements UserDetails {
    private String nombre;
    private String apellido;
    private String correo;
    private String clave;
    private Collection<? extends GrantedAuthority> authorities;

    public static UsuarioPrincipal build(UsuarioEntity usuarioEntity){
        List<GrantedAuthority> authorities =
                usuarioEntity.getRoles().stream().map(roleEntity -> new SimpleGrantedAuthority(roleEntity
                .getRoleNombre().name())).collect(Collectors.toList());
        return new UsuarioPrincipal(
                usuarioEntity.getNombre(),
                usuarioEntity.getApellido(),
                usuarioEntity.getCorreo(),
                usuarioEntity.getClave(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
}
