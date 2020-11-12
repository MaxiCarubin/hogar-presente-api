package ar.com.travelpaq.hogarpresente.api.security.dto;


import org.hibernate.validator.constraints.NotBlank;

public class LoginUsuario {
    @NotBlank
    private String correo;
    @NotBlank
    private String password;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
