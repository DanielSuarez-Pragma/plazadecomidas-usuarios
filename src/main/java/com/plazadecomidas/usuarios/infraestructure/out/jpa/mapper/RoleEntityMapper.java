package com.plazadecomidas.usuarios.infraestructure.out.jpa.mapper;

import com.plazadecomidas.usuarios.domain.model.Role;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RoleEntityMapper {
    default Role toRole(RoleEntity byName) {
        if ( byName == null ) {
            return null;
        }

        Role role = new Role();
        role.setId(byName.getId());
        role.setName(byName.getName());
        role.setDescription(byName.getDescription());
        return role;
    }
}