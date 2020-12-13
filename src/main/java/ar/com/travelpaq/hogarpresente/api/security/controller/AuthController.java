package ar.com.travelpaq.hogarpresente.api.security.controller;

import ar.com.travelpaq.hogarpresente.api.cloudinary.entity.ImagenEntity;
import ar.com.travelpaq.hogarpresente.api.cloudinary.repository.IImagenRepository;
import ar.com.travelpaq.hogarpresente.api.cloudinary.service.CloudinaryService;
import ar.com.travelpaq.hogarpresente.api.models.dto.Mensaje;
import ar.com.travelpaq.hogarpresente.api.security.entity.UsuarioEntity;
import ar.com.travelpaq.hogarpresente.api.security.service.IUsuarioService;
import ar.com.travelpaq.hogarpresente.api.security.dto.JwtDto;
import ar.com.travelpaq.hogarpresente.api.security.dto.LoginUsuario;
import ar.com.travelpaq.hogarpresente.api.security.dto.NuevoUsuario;
import ar.com.travelpaq.hogarpresente.api.security.entity.RoleEntity;
import ar.com.travelpaq.hogarpresente.api.security.enums.RoleNombre;
import ar.com.travelpaq.hogarpresente.api.security.jwt.JwtProvider;
import ar.com.travelpaq.hogarpresente.api.security.service.impl.RoleServiceImpl;
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
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private RoleServiceImpl rolService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private IImagenRepository imagenRepository;


    @GetMapping("/roles")
    public ResponseEntity<List<RoleEntity>> obtenerRoles(){
        return rolService.findAll();
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByCorreo(nuevoUsuario.getCorreo()))
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        ImagenEntity imagenEntity = new ImagenEntity();
        if(nuevoUsuario.getImagen() == null)
            imagenEntity = imagenRepository.findById(1).orElse(null);
        else
            imagenEntity = nuevoUsuario.getImagen();

        UsuarioEntity usuarioEntity =
                new UsuarioEntity
                    (
                        nuevoUsuario.getNombre(),
                        nuevoUsuario.getApellido(),
                        nuevoUsuario.getCorreo(),
                        passwordEncoder.encode(nuevoUsuario.getClave()),
                        nuevoUsuario.getEdad(),
                        imagenEntity,
                        nuevoUsuario.getEstudios()
                    );
        Set<RoleEntity> roles = new HashSet<>();
        if(nuevoUsuario.getRoles().contains("alumno"))
            roles.add(rolService.getByRoleNombre(RoleNombre.ROLE_ALUMNO).get());
        if(nuevoUsuario.getRoles().contains("capacitador"))
            roles.add(rolService.getByRoleNombre(RoleNombre.ROLE_CAPACITADOR).get());
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRoleNombre(RoleNombre.ROLE_ADMIN).get());
        usuarioEntity.setRoles(roles);
        usuarioService.save(usuarioEntity);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Email y/o contraseña incorrecta"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate
                        (
                                new UsernamePasswordAuthenticationToken
                                        (
                                                loginUsuario.getCorreo(),
                                                loginUsuario.getPassword()
                                        )
                        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        UsuarioEntity usuarioEntity = usuarioService.getByCorreo(userDetails.getUsername()).orElse(null);
        JwtDto jwtDto =
                new JwtDto
                        (
                                jwt,
                                userDetails.getUsername(),
                                userDetails.getAuthorities(),
                                usuarioEntity.getNombre(),
                                usuarioEntity.getApellido(),
                                usuarioEntity.getImagen(),
                                usuarioEntity.getId()
                        );
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

}
