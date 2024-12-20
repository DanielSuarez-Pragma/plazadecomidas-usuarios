package com.plazadecomidas.usuarios.application.handler;

import com.plazadecomidas.usuarios.application.dto.request.RoleRequestDto;
import com.plazadecomidas.usuarios.application.dto.response.RoleResponseDto;

import java.util.List;

public interface IRoleHandler {
    RoleResponseDto getRoleById(Long id);
    void saveRole(RoleRequestDto roleRequestDto);
    List<RoleResponseDto> getAllRoles();
    void deleteRoleById(Long id);
}
