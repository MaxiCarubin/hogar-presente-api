package ar.com.travelpaq.hogarpresente.api.security.service;

import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import ar.com.travelpaq.hogarpresente.api.models.services.IAlumnoService;
import ar.com.travelpaq.hogarpresente.api.security.entity.UsuarioPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IAlumnoService alumnoService;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        AlumnoEntity alumnoEntity = alumnoService.getByCorreo(correo).get();
        return UsuarioPrincipal.build(alumnoEntity);
    }
}
