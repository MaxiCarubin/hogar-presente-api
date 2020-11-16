package ar.com.travelpaq.hogarpresente.api.security.service;

import ar.com.travelpaq.hogarpresente.api.models.dto.AlumnoDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import ar.com.travelpaq.hogarpresente.api.security.dto.RoleDto;
import ar.com.travelpaq.hogarpresente.api.security.entity.RoleEntity;
import ar.com.travelpaq.hogarpresente.api.security.enums.RoleNombre;
import ar.com.travelpaq.hogarpresente.api.security.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleService {

    @Autowired
    IRoleRepository roleRepository;

    public Optional<RoleEntity> getByRoleNombre(RoleNombre roleNombre){
        return roleRepository.findByRoleNombre(roleNombre);
    }

    public ResponseEntity<List<RoleEntity>> findAll() {

        RoleEntity roleEntityADMIN = new RoleEntity(1, RoleNombre.ROLE_ADMIN);
        RoleEntity roleEntityUSER = new RoleEntity(2, RoleNombre.ROLE_ALUMNO);
        roleRepository.save(roleEntityADMIN);
        roleRepository.save(roleEntityUSER);
        List<RoleEntity> roleEntities = roleRepository.findAll();
        return new ResponseEntity(roleEntities, HttpStatus.OK);
    }
}
