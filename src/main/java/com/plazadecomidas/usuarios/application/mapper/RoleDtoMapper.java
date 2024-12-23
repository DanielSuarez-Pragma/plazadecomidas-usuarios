package com.plazadecomidas.usuarios.application.mapper;

import com.plazadecomidas.usuarios.application.dto.RoleDto;
import com.plazadecomidas.usuarios.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RoleDtoMapper {
    RoleDto toDto(Role role);
}
