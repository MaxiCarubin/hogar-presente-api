package ar.com.travelpaq.hogarpresente.api.security.service;

import ar.com.travelpaq.hogarpresente.api.security.entity.RoleEntity;
import ar.com.travelpaq.hogarpresente.api.security.enums.RoleNombre;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IRoleService {

    public Optional<RoleEntity> getByRoleNombre(RoleNombre roleNombre);

    public ResponseEntity<List<RoleEntity>> findAll();
}
