package ar.com.travelpaq.hogarpresente.api.models.services;

import ar.com.travelpaq.hogarpresente.api.models.dto.UsuarioDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.UsuarioEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IUsuarioService {

    Optional<UsuarioEntity> getByCorreo(String correo);

    public boolean existsByCorreo(String correo);

    public void save(UsuarioEntity usuarioEntity);

    public ResponseEntity<?> findAll();

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> create(UsuarioDto usuarioDto);

    public ResponseEntity<?> update(UsuarioDto usuarioDto, Long id);

    public ResponseEntity<?> delete(Long id);

}
