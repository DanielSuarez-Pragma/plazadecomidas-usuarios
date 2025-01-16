package com.plazadecomidas.usuarios.infraestructure.out.jpa.mapper;

import com.plazadecomidas.usuarios.domain.model.Role;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RoleEntityMapper {
    @Mapping(target = "name", source = "name")
    Role toRole(RoleEntity byName);
}