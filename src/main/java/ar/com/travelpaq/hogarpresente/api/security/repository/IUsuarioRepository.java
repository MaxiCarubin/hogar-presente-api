package ar.com.travelpaq.hogarpresente.api.security.repository;

import ar.com.travelpaq.hogarpresente.api.security.entity.RoleEntity;
import ar.com.travelpaq.hogarpresente.api.security.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByCorreo(String correo);

    boolean existsByCorreo(String correo);

    List<UsuarioEntity> findByOrderById();
}
