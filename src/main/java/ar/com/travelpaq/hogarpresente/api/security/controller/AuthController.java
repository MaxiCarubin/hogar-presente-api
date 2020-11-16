package ar.com.travelpaq.hogarpresente.api.security.controller;

import ar.com.travelpaq.hogarpresente.api.models.dto.Mensaje;
import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import ar.com.travelpaq.hogarpresente.api.models.services.IAlumnoService;
import ar.com.travelpaq.hogarpresente.api.security.dto.JwtDto;
import ar.com.travelpaq.hogarpresente.api.security.dto.LoginUsuario;
import ar.com.travelpaq.hogarpresente.api.security.dto.NuevoUsuario;
import ar.com.travelpaq.hogarpresente.api.security.dto.RoleDto;
import ar.com.travelpaq.hogarpresente.api.security.entity.RoleEntity;
import ar.com.travelpaq.hogarpresente.api.security.enums.RoleNombre;
import ar.com.travelpaq.hogarpresente.api.security.jwt.JwtProvider;
import ar.com.travelpaq.hogarpresente.api.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IAlumnoService alumnoService;

    @Autowired
    RoleService rolService;

    @Autowired
    JwtProvider jwtProvider;


    @GetMapping("/roles")
    public ResponseEntity<List<RoleEntity>> obtenerRoles(){
        return rolService.findAll();
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if(alumnoService.existsByCorreo(nuevoUsuario.getCorreo()))
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        AlumnoEntity alumnoEntity =
                new AlumnoEntity(nuevoUsuario.getNombre(), nuevoUsuario.getApellido(), nuevoUsuario.getCorreo(),
                        passwordEncoder.encode(nuevoUsuario.getClave()));
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(rolService.getByRoleNombre(RoleNombre.ROLE_ALUMNO).get());
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRoleNombre(RoleNombre.ROLE_ADMIN).get());
        alumnoEntity.setRoles(roles);
        alumnoService.save(alumnoEntity);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getCorreo(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

}
