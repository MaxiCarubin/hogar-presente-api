package ar.com.travelpaq.hogarpresente.api.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtDto {
    private String token;
    private String bearer = "Bearer";
    private String correo;
    private Collection<? extends GrantedAuthority>authorities;

    public JwtDto(String token, String correo, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.correo = correo;
        this.authorities = authorities;
    }
}
