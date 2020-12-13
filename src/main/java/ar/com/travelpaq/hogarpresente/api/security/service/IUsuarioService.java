package ar.com.travelpaq.hogarpresente.api.security.service;

import ar.com.travelpaq.hogarpresente.api.cloudinary.entity.ImagenEntity;
import ar.com.travelpaq.hogarpresente.api.security.dto.UsuarioDto;
import ar.com.travelpaq.hogarpresente.api.security.entity.UsuarioEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IUsuarioService {

    Optional<UsuarioEntity> getByCorreo(String correo);

    public boolean existsByCorreo(String correo);

    public void save(UsuarioEntity usuarioEntity);

    public ResponseEntity<?> findAll();

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> update(UsuarioDto usuarioDto, Long id);

    public ResponseEntity<?> delete(Long id);

//    public ResponseEntity<?> upload(MultipartFile archivo, Long id);
//
//    public ResponseEntity<?> verFoto(String nombreFoto);
}
