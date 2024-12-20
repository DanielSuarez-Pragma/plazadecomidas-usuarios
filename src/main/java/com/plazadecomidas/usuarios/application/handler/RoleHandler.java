package com.plazadecomidas.usuarios.application.handler;

import com.plazadecomidas.usuarios.application.dto.request.RoleRequestDto;
import com.plazadecomidas.usuarios.application.dto.response.RoleResponseDto;
import com.plazadecomidas.usuarios.application.mapper.IRoleRequestMapper;
import com.plazadecomidas.usuarios.application.mapper.IRoleResponseMapper;
import com.plazadecomidas.usuarios.domain.api.IRoleServicePort;
import com.plazadecomidas.usuarios.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleHandler implements IRoleHandler {

    private final IRoleServicePort roleServicePort;
    private final IRoleRequestMapper roleRequestMapper;
    private final IRoleResponseMapper roleResponseMapper;

    @Override
    public RoleResponseDto getRoleById(Long id) {
        return roleResponseMapper.toResponse(roleServicePort.getRoleById(id));
    }

    @Override
    public void saveRole(RoleRequestDto roleRequestDto) {
        Role role = roleRequestMapper.toRole(roleRequestDto);
        roleServicePort.saveRole(role);
    }

    @Override
    public List<RoleResponseDto> getAllRoles() {
        return roleResponseMapper.toResposeList(roleServicePort.getAllRoles());
    }

    @Override
    public void deleteRoleById(Long id) {
        roleServicePort.deleteRoleById(id);
    }
}
