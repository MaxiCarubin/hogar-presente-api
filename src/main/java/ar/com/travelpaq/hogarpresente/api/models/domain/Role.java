package ar.com.travelpaq.hogarpresente.api.models.domain;

import ar.com.travelpaq.hogarpresente.api.models.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private long id;

    private String nombre;

    public RoleEntity convertToRoleEntity(Role role) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(role.getId());
        roleEntity.setNombre(role.getNombre());
        return roleEntity;
    }
}
