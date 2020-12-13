package ar.com.travelpaq.hogarpresente.api.security.service;

import ar.com.travelpaq.hogarpresente.api.security.dto.UpdateUsuarioDto;
import ar.com.travelpaq.hogarpresente.api.security.entity.UsuarioEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface IUsuarioService {

    Optional<UsuarioEntity> getByCorreo(String correo);

    public boolean existsByCorreo(String correo);

    public void save(UsuarioEntity usuarioEntity);

    public ResponseEntity<?> findAll();

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> update(UpdateUsuarioDto updateUsuarioDto, Long id);

    public ResponseEntity<?> delete(Long id);

    public ResponseEntity<?> updateImg(MultipartFile multipartFile, Long id) throws IOException;

//    public ResponseEntity<?> upload(MultipartFile archivo, Long id);
//
//    public ResponseEntity<?> verFoto(String nombreFoto);
}
