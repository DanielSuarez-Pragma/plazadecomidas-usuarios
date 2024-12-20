package com.plazadecomidas.usuarios.domain.api;

import com.plazadecomidas.usuarios.domain.model.Role;

import java.util.List;

public interface IRoleServicePort {
    Role getRoleById(Long id);
    Role saveRole(Role role);
    List<Role> getAllRoles();
    void deleteRoleById(Long id);
}
