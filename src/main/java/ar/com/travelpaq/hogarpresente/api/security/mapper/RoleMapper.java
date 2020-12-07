package ar.com.travelpaq.hogarpresente.api.security.mapper;

import ar.com.travelpaq.hogarpresente.api.security.dto.RoleDto;
import ar.com.travelpaq.hogarpresente.api.security.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleDto mapRoleDtoToRoleEntity(RoleEntity roleEntity){
        RoleDto roleDto = new RoleDto();
        roleDto.setId(roleEntity.getId());
        roleDto.setRoleNombre(roleEntity.getRoleNombre());
        return roleDto;
    }
}
